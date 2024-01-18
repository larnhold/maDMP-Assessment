package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiMetricTestScore(
    @JsonProperty("earned")
    val earned: Int,
    @JsonProperty("total")
    val total: Int
)
