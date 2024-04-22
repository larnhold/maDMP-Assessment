package org.arnhold.dmpeval.casestudy.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.arnhold.sdk.vocab.dmp.DMP

data class DMPWrapper(
    @JsonProperty("dmp")
    val dmp: DMP
)
