package org.arnhold.sdk.model

data class EvaluationReportParameters(
    val evaluationId: String,
    val aggregateDimensions: List<String>,
    val averageDimensions: List<String>
)
