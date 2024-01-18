package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiMetricTest(
    @JsonProperty("metric_test_name")
    val name: String,
    @JsonProperty("metric_test_score")
    val metricTestScore: FujiMetricTestScore,
    @JsonProperty("metric_test_maturity")
    val metricTestMaturity: Int,
    @JsonProperty("metric_test_status")
    val metricTestStatus: String
)
