package org.arnhold.evaluator.indicator.evaluationManager

import org.arnhold.sdk.vocab.constants.DataLifecycle

data class EvaluationTaskParameters (
    val dmpLoaderParameters: DMPLoaderParameters,
    val contextLoaderParameters: String,
    val evaluatorParameters: String,
    val dataLifecycle: DataLifecycle
)