package org.arnhold.evaluator.indicator.evaluationManager

import org.arnhold.sdk.common.constants.DataLifecycle

data class EvaluationTaskParameters (
    val dmpLoaderParameters: DMPLoaderParameters,
    val dataLifecycle: DataLifecycle
) {
}