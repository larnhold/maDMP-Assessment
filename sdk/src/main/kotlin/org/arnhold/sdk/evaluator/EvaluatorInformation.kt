package org.arnhold.sdk.evaluator

import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension

data class EvaluatorInformation(
    var applicableDimension: Dimension,
    var belongsToCategory: Category
)