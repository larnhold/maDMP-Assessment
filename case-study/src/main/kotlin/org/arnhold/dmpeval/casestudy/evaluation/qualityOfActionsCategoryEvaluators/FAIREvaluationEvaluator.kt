package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.dmpeval.casestudy.evaluation.SoftareAgents
import org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators.AvailabilityEvaluator
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiMetricTest
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiResult
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiRoot
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.*
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class FAIREvaluationEvaluator @Autowired constructor(
    val fujiService: FujiService,
    val sparqlSelector: SparqlSelector,
) : EvaluatorPlugin {

    private val logger = KotlinLogging.logger {}

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.FAIR.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.FAIR_DIMENSION,
            CategoryDimmensionModels.QUALITY_OF_ACTIONS_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(dmp: Model, context: List<DMPContext>, lifecycle: DataLifecycle): List<Measurement> {
        logger.info { "Get measurements for all datasets" }
        val query = Path.of(AvailabilityEvaluator.SPARQL_DIRECTORY + "allDatasets.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} Datasets with identifiers"}

        val measurements = selected.flatMap {
            val value = it.literals.get("idValue").toString()
            val type = it.literals.get("idType").toString()
            val datasetIdType = it.resources.get("datasetIdType")
            val dataset = it.resources.get("dataset")

            logger.info { "Starting F-UJI evaluation on dataset ${dataset.toString()}" }
            evaluate(value, type, datasetIdType, dataset)
        }
        logger.info { "F-UJI returned ${measurements.size} measurements on ${selected.size} datasets" }
        logger.info { "All FAIR measurements calculated" }
        return measurements
    }

    private fun evaluate(value: String, type: String, datasetIdType: Resource?, dataset: Resource?): List<Measurement> {
        logger.info { "Start evaluation of $value with type $type" }
        val fujiResult = fujiService.evaluateResource(value)
        return if (dataset != null && datasetIdType != null) {
            val measurements = fujiResult?.let { fujiResultRootToMeasurement(dataset, datasetIdType, it) }
            return measurements ?: listOf()
        } else {
            listOf()
        }
    }

    private fun fujiResultRootToMeasurement(dataset: Resource, datasetIdType: Resource, result: FujiRoot): List<Measurement> {
        logger.info { "Convert ${result.results.size} evaluation result from F-UJI to DQV" }
        return result.results.map { fujiResultToMeasurement(dataset, datasetIdType, it) }
    }

    private fun fujiResultToMeasurement(dataset: Resource, datasetIdType: Resource, result: FujiResult): Measurement {
        logger.info { "Convert metric ${result.metricIdentifier} from F-UJI to DQV" }
        val metric: Metric = QualityOfActionsMetricModels.metricFromFujiResult(datasetIdType, result)
        return Measurement(
            DmpLifecycle(DataLifecycle.PUBLISHED),
            metric,
            Guidance("", ""),
            DMPLocation(null, dataset, null),
            result.score.earned,
            softwareAgent = SoftareAgents.FUJI,
            testResults =  metric.metricTests.mapNotNull { testResultFrom(it, result.metricTests) }
        )
    }

    private fun testResultFrom(metricTestDefinition: MetricTestDefinition?, fujiTestResults: Map<String, FujiMetricTest>): TestResult? {
        val fujiTestResultValue = fujiTestResults.get(metricTestDefinition?.title)
        return if (fujiTestResultValue != null && metricTestDefinition !== null) {
            TestResult(
                metricTestDefinition,
                fujiTestResultValue.metricTestScore.earned
            )
        } else {
            null
        }
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}