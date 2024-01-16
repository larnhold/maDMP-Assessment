package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiResult(
    @JsonProperty("id")
    var id: Int?,
    @JsonProperty("metric_identifier")
    var metricIdentifier: String?,
    @JsonProperty("metric_name")
    var metricName: String?,
    @JsonProperty("score")
    var score: FujiScore?,
    @JsonProperty("maturity")
    var maturity: Int?
)
