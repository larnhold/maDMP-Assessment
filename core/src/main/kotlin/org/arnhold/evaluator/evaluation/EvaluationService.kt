package org.arnhold.evaluator.evaluation

interface EvaluationService {
    fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult
}