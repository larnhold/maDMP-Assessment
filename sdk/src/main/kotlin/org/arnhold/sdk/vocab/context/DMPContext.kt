package org.arnhold.sdk.vocab.context

import org.arnhold.sdk.vocab.dqv.DMPLocation

data class DMPContext(
    val dmpLocation: DMPLocation,
    val sourceIdentifier: String,
    val value: String,
    val vocabularyIdentifier: String
)
