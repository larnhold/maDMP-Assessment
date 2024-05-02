package org.arnhold.sdk.vocab.context

import org.arnhold.sdk.vocab.constants.ContextSchema

data class DMPContext(
    val dmpLocation: ContextDMPLocation,
    val sourceIdentifier: String,
    val value: String,
    val vocabularyIdentifier: ContextSchema
)
