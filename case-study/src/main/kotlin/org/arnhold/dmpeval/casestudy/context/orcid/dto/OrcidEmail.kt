package org.arnhold.dmpeval.casestudy.context.orcid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OrcidEmail(
    @JsonProperty("value")
    val value: String
)
