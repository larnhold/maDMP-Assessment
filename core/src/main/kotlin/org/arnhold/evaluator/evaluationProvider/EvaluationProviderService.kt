package org.arnhold.evaluator.evaluationProvider

import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin

interface EvaluationProviderService {
    fun getAllEvaluators(): List<DimensionEvaluatorPlugin>
}