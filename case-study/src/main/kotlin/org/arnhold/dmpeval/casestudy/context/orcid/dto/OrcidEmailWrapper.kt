package org.arnhold.dmpeval.casestudy.context.orcid.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OrcidEmailWrapper(
    @JsonProperty("emails")
    val emails: List<OrcidEmail>
)