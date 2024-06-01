package org.arnhold.evaluator.indicator.metricAggregation

interface MetricAggregationService {
    fun averageForDimension(dimension: String)
    fun averageForCategory(category: String)
    fun sumForCategory(category: String)
    fun sumForDimension(dimension: String)
}