package org.arnhold.evaluator.indicator.metricAggregation

import org.arnhold.sdk.vocab.dqv.Measurement

interface MetricAggregationService {
    fun averageForDimension(dimension: String, measurements: List<Measurement>): Double
    fun sumForDimension(dimension: String, measurements: List<Measurement>): Double
}