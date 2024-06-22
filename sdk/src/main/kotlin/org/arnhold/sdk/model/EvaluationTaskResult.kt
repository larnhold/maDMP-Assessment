package org.arnhold.sdk.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.arnhold.sdk.vocab.dqv.Measurement
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class EvaluationTaskResult(
        val dmpStoreId: UUID,
        val evaluationId: UUID,
        val measurements: List<Measurement> = ArrayList()
)
