package org.arnhold.sdk.evaluator

import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Metric

data class EvaluatorInformation(
    var applicableDimension: Dimension,
    var belongsToCategory: Category,
    var metricBlueprints: List<Metric>
)