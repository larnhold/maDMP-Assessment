package org.arnhold.evaluator.evaluationManager

import org.arnhold.sdk.common.dqv.Measurement

data class EvaluationTaskResult(
        val success: Boolean? = null,
        val message: String? = null,
        val evaluationId: String? = null,
        val measurements: List<Measurement>?
)
