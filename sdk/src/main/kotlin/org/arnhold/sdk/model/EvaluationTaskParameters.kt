package org.arnhold.sdk.model

import org.arnhold.sdk.vocab.constants.DataLifecycle

data class EvaluationTaskParameters (
    val dmpLoaderParameters: DMPLoaderParameters,
    val dataLifecycle: DataLifecycle,
    val dimensions: List<String>?,
    val categories: List<String>?
)