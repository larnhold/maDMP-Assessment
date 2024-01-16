package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji

import com.fasterxml.jackson.annotation.JsonProperty

data class FujiEvaluatePayload(
    @JsonProperty("metadata_service_endpoint")
    val metadata_service_endpoint: String = "https://ws.pangaea.de/oai/provider",
    @JsonProperty("metadata_service_type")
    val metadata_service_type: String = "oai_pmh",
    @JsonProperty("metric_version")
    val metric_version: String = "metrics_v0.5",
    @JsonProperty("object_identifier")
    val object_identifier: String,
    @JsonProperty("test_debug")
    val test_debug: Boolean = true,
    @JsonProperty("use_datacite")
    val use_datacite: Boolean = true
)
