package org.arnhold.sdk.common.dqv

import org.arnhold.sdk.common.constants.DataLifecycle

data class Measurement(
    val lifeCycleStage: DataLifecycle,
    val isMeasurementOf: Metric,
    val guidance: Guidance,
    val computedOn: String,
    val value: String
)
