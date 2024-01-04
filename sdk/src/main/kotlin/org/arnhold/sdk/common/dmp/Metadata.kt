package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

data class Metadata (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("language")
    val language: String?,
    @JsonProperty("metadata_standard_id")
    val metadataStandardId: MetadataStandardId?
): RdfResourceProvider {
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}