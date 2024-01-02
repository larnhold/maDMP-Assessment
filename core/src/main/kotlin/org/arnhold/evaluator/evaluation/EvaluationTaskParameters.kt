package org.arnhold.evaluator.evaluation

import org.arnhold.sdk.common.enum.DataLifecycle

data class EvaluationTaskParameters (
        val dmpLoaderParameters: DMPLoaderParameters,
        val dataLifecycle: DataLifecycle
) {
}