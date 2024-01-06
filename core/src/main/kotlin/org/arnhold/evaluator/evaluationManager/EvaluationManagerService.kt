package org.arnhold.evaluator.evaluationManager

import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension

interface EvaluationManagerService {
    fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult
    fun getEvaluatorInformation(): Map<Category, List<Dimension>>
}