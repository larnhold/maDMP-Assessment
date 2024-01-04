package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

data class MetadataStandardId(
    @JsonProperty("identifier")
    val identifier: String?,
    @JsonProperty("type")
    val type: String?
): RdfResourceProvider {
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}
