package org.arnhold.evaluator.evaluation

import org.arnhold.sdk.common.enum.DataLifecycle

data class EvaluationTaskParameters (
        val dmpLoader: String,
        val dmpIdentifier: String,
        val dataLifecycle: DataLifecycle
) {
}