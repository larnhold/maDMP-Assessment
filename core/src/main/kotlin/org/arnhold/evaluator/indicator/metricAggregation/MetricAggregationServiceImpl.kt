package org.arnhold.evaluator.indicator.metricAggregation

import org.arnhold.sdk.vocab.dqv.Measurement
import org.springframework.stereotype.Component

@Component
class MetricAggregationServiceImpl: MetricAggregationService {

    override fun averageForDimension(dimension: String, measurements: List<Measurement>): Double {
        val count = measurements.filter { it.isMeasurementOf.inDimension?.title.equals(dimension, ignoreCase = true)}.size
        return sumForDimension(dimension, measurements) / count
    }

    override fun sumForDimension(dimension: String, measurements: List<Measurement>): Double {
        val selected = measurements.filter { it.isMeasurementOf.inDimension?.title.equals(dimension, ignoreCase = true)}
        var sum = 0

        for (measurement in selected) {
            when (measurement.isMeasurementOf.expectedDataType) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> sum += (if(measurement.value === "true") 1 else 0)
            }
        }

        return sum.toDouble()
    }
}