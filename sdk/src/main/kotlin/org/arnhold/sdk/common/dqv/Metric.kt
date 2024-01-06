package org.arnhold.sdk.common.dqv

import org.arnhold.sdk.common.constants.DataLifecycle

class Metric (
    val description: String,
    val inDimension: Dimension,
    val applicableDMPLifeCycles: List<DataLifecycle>
)
