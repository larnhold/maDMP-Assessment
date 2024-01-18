package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiRoot(
    @JsonProperty("metric_specification")
    var metricSpecification: String,
    @JsonProperty("metric_version")
    var metricVersion: String,
    @JsonProperty("total_metrics")
    var totalMetrics: Int,
    @JsonProperty("summary")
    var summary: FujiSummary,
    @JsonProperty("results")
    var results: List<FujiResult>
)
