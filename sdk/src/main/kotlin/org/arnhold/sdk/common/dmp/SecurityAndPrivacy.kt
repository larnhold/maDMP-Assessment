package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

data class SecurityAndPrivacy (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("title")
    val title: String?
): RdfResourceProvider {
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}