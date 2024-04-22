package org.arnhold.sdk.evaluator

import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension
import org.arnhold.sdk.vocab.dqv.Metric

data class EvaluatorInformation(
    var applicableDimension: Dimension,
    var belongsToCategory: Category,
    var metricBlueprints: List<Metric>
)