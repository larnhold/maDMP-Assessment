package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiSummary(
    @JsonProperty("score_earned")
    val scoreEarned: Map<String, Int>,
    @JsonProperty("score_total")
    val scoreTotal: Map<String, Int>,
    @JsonProperty("score_percent")
    val scorePercent: Map<String, Double>,
    @JsonProperty("status_total")
    val statusTotal: Map<String, Int>,
    @JsonProperty("status_passed")
    val statusPassed: Map<String, Int>,
    @JsonProperty("maturity")
    val maturity: Map<String, Double>,
)
