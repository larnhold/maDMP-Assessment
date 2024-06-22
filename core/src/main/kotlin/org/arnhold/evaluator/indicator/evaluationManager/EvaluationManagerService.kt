package org.arnhold.evaluator.indicator.evaluationManager

import org.arnhold.sdk.model.EvaluationReport
import org.arnhold.sdk.model.EvaluationReportParameters
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.model.EvaluationTaskResult
import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension

interface EvaluationManagerService {
    fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult
    fun getEvaluatorInformation(): Map<Category, List<Dimension>>
    fun createEvaluationReport(parameters: EvaluationReportParameters): EvaluationReport
}