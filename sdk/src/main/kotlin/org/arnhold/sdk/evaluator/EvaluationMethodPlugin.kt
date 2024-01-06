package org.arnhold.sdk.evaluator

import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.plugin.ConfigurablePlugin

interface EvaluationMethodPlugin: ConfigurablePlugin<String, EvaluatorInformation> {

    fun suitableForDimension(dimension: Dimension): Boolean
    fun suitableForMetric(metric: Metric): Boolean
}