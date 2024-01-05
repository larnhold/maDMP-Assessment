package org.arnhold.dmpeval.casestudy.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.DMP
import org.arnhold.sdk.common.dmp.RdfResourceProvider

data class DMPWrapper(
    @JsonProperty("dmp")
    val dmp: DMP
)
