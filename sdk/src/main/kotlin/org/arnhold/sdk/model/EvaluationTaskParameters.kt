package org.arnhold.sdk.model

import org.arnhold.sdk.vocab.constants.DataLifecycle

data class EvaluationTaskParameters (
    val dmpLoaderParameters: DMPLoaderParameters,
    val dataLifecycle: DataLifecycle,
    val dimensions: List<String> = listOf(),
    val categories: List<String> = listOf()
)