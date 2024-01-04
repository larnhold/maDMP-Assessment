package org.arnhold.evaluator.methodProvision

import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin

interface MethodProviderService {
    fun getSuitableEvaluatorForMetric(metric: Metric): EvaluationMethodPlugin?
}