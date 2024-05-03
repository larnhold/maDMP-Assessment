package org.arnhold.evaluator.indicator.evaluationManager

import org.arnhold.sdk.model.EvaluationTaskResult
import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension

interface EvaluationManagerService {
    fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult
    fun getEvaluatorInformation(): Map<Category, List<Dimension>>
}