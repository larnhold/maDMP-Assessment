package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class Project (
    @JsonProperty("funding")
    val fundings: List<Funding>?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("end")
    val end: String?,
    @JsonProperty("start")
    val start: String?,
    @JsonProperty("title")
    val title: String?
) : RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}