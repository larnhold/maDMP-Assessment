package org.arnhold.evaluator.metricProvider

import org.arnhold.evaluator.model.Dimension
import org.arnhold.sdk.model.metric.Metric

interface MetricProvider {
    fun provideMetric(dimension: Dimension): Metric
}