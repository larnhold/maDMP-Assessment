package org.arnhold.dmpeval.casestudy.context.orcid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Orcid (
    @JsonProperty("displayName")
    val name: String,
    @JsonProperty("emails")
    val emails: OrcidEmailWrapper
)