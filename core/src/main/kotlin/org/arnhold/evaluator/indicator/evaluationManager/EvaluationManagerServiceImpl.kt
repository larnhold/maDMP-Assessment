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
import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension
import org.arnhold.sdk.vocab.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EvaluationManagerServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
    val dataProviderService: DataProviderService
) : EvaluationManagerService {

    private val logger = KotlinLogging.logger {}
    override fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        logger.info { "Create evaluation with parameters $parameters" }

        val contextDMPId = dataProviderService.loadContextualizedDMP(parameters.dmpLoaderParameters)
        val contextDMP = dataProviderService.getContextualizedDMP(contextDMPId)

        val measurements = runBlocking(Dispatchers.Default) {
            return@runBlocking evaluationProviderService.produceAllMeasurements(contextDMP, parameters.dataLifecycle)
        }

        logger.info { "Created ${measurements.size} measurements" }
        saveMeasurements(measurements)

        return EvaluationTaskResult(
            success = true,
            message = "",
            evaluationId = UUID.randomUUID().toString(),
            measurements = measurements
        )
    }

    private fun saveMeasurements(measurements: List<Measurement>) {
        val measurementsModel = measurementsToModel(dataProviderService.getDMPDQVOntology(), measurements)
        logger.info { "Reason over measurements" }
        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(dataProviderService.getDMPDQVOntology())
        val reasonedMeasurementsModel = ModelFactory.createInfModel(reasoner, measurementsModel)
        dataProviderService.saveModel(reasonedMeasurementsModel)
    }

    override fun getEvaluatorInformation(): Map<Category, List<Dimension>> {
        return evaluationProviderService.getAllEvaluators().map { it.getPluginInformation() }.groupBy ( {it.belongsToCategory}, {it.applicableDimension} )
    }

    private fun measurementsToModel(dmpdqv: OntModel, measurements: List<Measurement>): Model {
        logger.info { "Integrate measurements into semantic DMP model" }
        val model = dmpdqv.baseModel
        measurements.mapIndexed {index, measurement -> measurement.toResource(dmpdqv, "Measurement_$index")}
        return model
    }
}