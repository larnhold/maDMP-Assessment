package org.arnhold.evaluator.indicator.metricProcessing

import kotlinx.coroutines.*
import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.indicator.evaluationProvider.EvaluationProviderService
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MetricProcessingServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
) : MetricProcessingService {

    private val logger = KotlinLogging.logger {}

    override fun produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement> {
        TODO("Not yet implemented")
    }

    override suspend fun produceAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle $lifecycle" }
        return coroutineScope {
            return@coroutineScope evaluationProviderService.getAllEvaluators().map { async { it.getAllMeasurements(dmp, lifecycle) } }.awaitAll().flatten()
        }
    }
}