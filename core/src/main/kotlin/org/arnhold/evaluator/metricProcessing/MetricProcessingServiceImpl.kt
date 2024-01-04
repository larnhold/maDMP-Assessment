package org.arnhold.evaluator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.methodProvision.MethodProviderService
import org.arnhold.sdk.common.dqv.Measurement
import org.arnhold.sdk.common.dqv.Method
import org.arnhold.sdk.common.dqv.Metric
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MetricProcessingServiceImpl @Autowired constructor(
    val methodProviderService: MethodProviderService
) : MetricProcessingService {

    override fun produceMeasurements(dmp: Model): List<Measurement> {
        val metricDefinitions = getMetricDefinitions().forEach { (key, value) -> println("$key = $value") }
        return listOf();
    }

    private fun produceSingleMeasurement(dmp: Model, metric: Metric) {
    }

    private fun getMetricDefinitions(): Map<Metric, Method> {
        return mapOf()
    }
}