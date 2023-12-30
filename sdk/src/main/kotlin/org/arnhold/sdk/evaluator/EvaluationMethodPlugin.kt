package org.arnhold.sdk.evaluator

import org.arnhold.sdk.common.dqv.Metric
import org.springframework.plugin.core.Plugin

interface EvaluationMethodPlugin: Plugin<Metric> {
}