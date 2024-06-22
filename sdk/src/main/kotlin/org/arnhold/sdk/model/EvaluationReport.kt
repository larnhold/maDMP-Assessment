package org.arnhold.sdk.model

import org.arnhold.sdk.vocab.dqv.Measurement


data class EvaluationReport (
    var dmp: String? = null,
    var dmpFormat: String? = null,
    var measurements: List<Measurement> = listOf(),
    var sums: Map<String, Double> = mapOf(),
    var averages: Map<String, Double> = mapOf()
)