package org.arnhold.evaluator.indicator.evaluationProvider

import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin

interface EvaluationProviderService {
    fun getAllEvaluators(): List<DimensionEvaluatorPlugin>
}