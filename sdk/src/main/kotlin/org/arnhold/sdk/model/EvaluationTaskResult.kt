package org.arnhold.sdk.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.arnhold.sdk.vocab.dqv.Measurement

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EvaluationTaskResult(
        val evaluationId: String? = null,
        val measurements: List<Measurement> = ArrayList()
)
