package org.arnhold.sdk.vocab.context

import org.arnhold.sdk.vocab.constants.ContextSchema

data class DMPContext(
    val dmpLocations: List<ContextDMPLocation>,
    val sourceIdentifier: String,
    val value: String?,
    val vocabularyIdentifier: ContextSchema
)
