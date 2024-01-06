package org.arnhold.evaluator.evaluationManager

import org.arnhold.sdk.common.constants.DataLifecycle

data class EvaluationTaskParameters (
        val dmpLoaderParameters: DMPLoaderParameters,
        val dataLifecycle: DataLifecycle
) {
}