package org.arnhold.evaluator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.methodProvision.MethodProviderService
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Measurement
import org.arnhold.sdk.common.dqv.Metric
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MetricProcessingServiceImpl @Autowired constructor(
    val methodProviderService: MethodProviderService
) : MetricProcessingService {

    override fun <T> produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement<T>> {
        return listOf()
    }

    private fun produceSingleMeasurement(dmp: Model, metric: Metric) {
    }
}