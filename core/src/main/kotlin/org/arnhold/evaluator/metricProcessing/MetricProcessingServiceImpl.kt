package org.arnhold.evaluator.metricProcessing

import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.evaluationProvider.EvaluationProviderService
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Measurement
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV
import org.arnhold.sdk.tools.SparqlSelector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class MetricProcessingServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
    val sparqlSelector: SparqlSelector,
) : MetricProcessingService {

    private val logger = KotlinLogging.logger {}

    companion object {
        const val SPARQL_DIRECTORY = "data/selectors/"
    }

    override fun produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement> {
        TODO("Not yet implemented")
    }

    override fun produceAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle $lifecycle" }
        val measurements = evaluationProviderService.getAllEvaluators().flatMap { it.getAllMeasurements(dmp, lifecycle) }
        addToDmp(dmp, measurements)
        return measurements
    }

    private fun addToDmp(dmp: Model, measurements: List<Measurement>) {
        logger.info { "Integrate measurements into semantic DMP model" }
        val measurementResources = measurements.mapIndexed {index, measurement -> measurement.toResource(dmp, "Measurement_$index")}
        val query = Path.of(SPARQL_DIRECTORY + "dmps.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        selected.forEach { dmpResource ->
            val dmpRoot = dmpResource.resources.get("subject")
            measurementResources.forEach {measurementResource ->
                logger.info { "Add measurement ${measurementResource.uri}" }
                dmpRoot?.addProperty(DMPDQV.HAS_QUALITY_MEASUREMENT, measurementResource)
            }
        }
    }
}