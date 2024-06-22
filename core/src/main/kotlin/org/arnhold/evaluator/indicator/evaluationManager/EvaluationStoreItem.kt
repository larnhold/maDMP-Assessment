package org.arnhold.evaluator.indicator.evaluationManager

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Measurement
import java.util.UUID

data class EvaluationStoreItem(
    val evaluationId: UUID,
    val dmpId: UUID,
    val dmp: Model,
    val measurements: List<Measurement>,
    val context: List<DMPContext>
)
