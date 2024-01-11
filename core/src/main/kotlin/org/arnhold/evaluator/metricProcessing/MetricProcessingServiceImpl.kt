package org.arnhold.evaluator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.evaluationProvider.EvaluationProviderService
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MetricProcessingServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService
) : MetricProcessingService {

    override fun produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement> {
        TODO("Not yet implemented")
    }

    override fun produceAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement> {
        return evaluationProviderService.getAllEvaluators().flatMap { it.getAllMeasurements(dmp, lifecycle) }
    }
}