package org.arnhold.sdk.evaluator

import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.plugin.ConfigurablePlugin
import org.springframework.plugin.core.Plugin

interface EvaluationMethodPlugin: ConfigurablePlugin<Metric> {
    fun suitableForMetric(metric: Metric): Boolean
}