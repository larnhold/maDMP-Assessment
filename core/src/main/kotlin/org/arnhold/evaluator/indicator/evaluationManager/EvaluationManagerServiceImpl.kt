package org.arnhold.evaluator.indicator.evaluationManager

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.reasoner.Reasoner
import org.apache.jena.reasoner.ReasonerRegistry
import org.arnhold.evaluator.harvester.dataProvider.DataProviderService
import org.arnhold.evaluator.indicator.evaluationProvider.EvaluationProviderService
import org.arnhold.evaluator.indicator.metricAggregation.MetricAggregationService
import org.arnhold.sdk.model.EvaluationReport
import org.arnhold.sdk.model.EvaluationReportParameters
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.model.EvaluationTaskResult
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension
import org.arnhold.sdk.vocab.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.io.StringWriter
import java.util.*


@Component
class EvaluationManagerServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
    val dataProviderService: DataProviderService,
    val runtimeStore: RuntimeStore,
    val aggregationService: MetricAggregationService
) : EvaluationManagerService {

    private val logger = KotlinLogging.logger {}


    override fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        logger.info { "Create evaluation with parameters $parameters" }

        // dmp
        logger.info { "Get DMP" }
        val dmpStoreId = dataProviderService.loadDMP(parameters.dmpLoaderParameters)
        val dmp = dataProviderService.getModel(dmpStoreId)
        val dmpOntology: OntModel = dataProviderService.getDCSOntology()
        val extensions: Map<Extension, OntModel> = dataProviderService.getExtensions()

        //context
        logger.info { "Get Context" }
        val context = dataProviderService.loadContext(dmp)

        //measurements
        logger.info { "Get Measurements" }
        val measurements = getMeasurementsDependingOnParameters(
            dmp, context, parameters, dmpOntology, extensions
        )

        logger.info { "Created ${measurements.size} measurements" }
        val evaluationId = UUID.randomUUID()

        val result = EvaluationTaskResult(
            dmpStoreId = dmpStoreId,
            evaluationId = evaluationId,
            measurements = measurements
        )

        logger.info { "Persist Measurements and other Evaluation Artifacts" }
        saveMeasurements(result)
        runtimeStore.storePackage(EvaluationStoreItem(evaluationId, dmpStoreId, dmp, measurements, context))
        return result
    }

    private fun getMeasurementsDependingOnParameters(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        return runBlocking(Dispatchers.Default) {
            if (parameters.dimensions.isNotEmpty()) {
                logger.info { "Call Evaluation Provider -> Produce Measurements for Dimensions ${parameters.dimensions}" }
                return@runBlocking evaluationProviderService.produceMeasurementsForDimensions(dmp, context, parameters, dmpOntology, extensionOntologies)
            } else if (parameters.categories.isNotEmpty()) {
                logger.info { "Call Evaluation Provider -> Produce Measurements for Categories ${parameters.categories}" }
                return@runBlocking evaluationProviderService.produceMeasurementsForCategories(dmp, context, parameters, dmpOntology, extensionOntologies)
            } else {
                logger.info { "Call Evaluation Provider -> Produce all Measurements" }
                return@runBlocking evaluationProviderService.produceAllMeasurements(dmp, context, parameters, dmpOntology, extensionOntologies)
            }
        }
    }

    private fun saveMeasurements(result: EvaluationTaskResult) {
        val measurementsModel = measurementsToModel(dataProviderService.getDMPDQVOntology(), result.measurements)
        logger.info { "Reason over measurements" }
        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(dataProviderService.getDMPDQVOntology())
        val reasonedMeasurementsModel = ModelFactory.createInfModel(reasoner, measurementsModel)
        val uuid = dataProviderService.saveModel(reasonedMeasurementsModel)
        logger.info { "Measurements saved with id $uuid" }
        dataProviderService.saveAsJson<EvaluationTaskResult>(result, uuid)
    }

    override fun getEvaluatorInformation(): Map<Category, List<Dimension>> {
        return evaluationProviderService.getAllEvaluators().map { it.getPluginInformation() }.groupBy ( {it.belongsToCategory}, {it.applicableDimension} )
    }

    override fun createEvaluationReport(parameters: EvaluationReportParameters): EvaluationReport {
        logger.info { "Create evaluation report for evaluation with id ${parameters.evaluationId}" }
        val report = EvaluationReport()

        val storeItem = runtimeStore.store.find { it.evaluationId.toString() == parameters.evaluationId }
        if (storeItem == null) { throw ResponseStatusException(HttpStatus.NOT_FOUND, "No evaluation exists") }
        logger.info { "Received corresponding evaluation artifacts" }

        val dmpSerialized = StringWriter()
        storeItem.dmp.write(dmpSerialized, "RDF/JSON")

        report.dmp = dmpSerialized.toString()
        report.dmpFormat = "RDF/JSON"
        report.measurements = storeItem.measurements

        logger.info { "Calculate averages for ${parameters.averageDimensions.size} dimensions" }
        report.averages = parameters.averageDimensions.associateBy({ it }, { aggregationService.averageForDimension(it, report.measurements) })
        logger.info { "Calculate sums for ${parameters.aggregateDimensions.size} dimensions" }
        report.sums = parameters.averageDimensions.associateBy({ it }, { aggregationService.sumForDimension(it, report.measurements) })

        return report
    }

    private fun measurementsToModel(dmpdqv: OntModel, measurements: List<Measurement>): Model {
        logger.info { "Convert measurements to DMPQV" }
        val model = dmpdqv.baseModel
        measurements.mapIndexed {index, measurement -> measurement.toResource(dmpdqv, "Measurement_$index")}
        return model
    }
}