package org.arnhold.sdk.model

import org.arnhold.sdk.vocab.dqv.Measurement

data class EvaluationTaskResult(
        val success: Boolean? = null,
        val message: String? = null,
        val evaluationId: String? = null,
        val measurements: List<Measurement> = ArrayList()
)
