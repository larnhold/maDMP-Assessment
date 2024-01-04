package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

data class Distribution (
    @JsonProperty("license")
    val licenses: List<License>?,
    @JsonProperty("host")
    val host: Host?,
    @JsonProperty("access_url")
    val accessUrl: String?,
    @JsonProperty("available_until")
    val availableUntil: String?,
    @JsonProperty("byte_size")
    val byteSize: Int?,
    @JsonProperty("data_access")
    val dataAccess: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("download_url")
    val downloadUrl: String?,
    @JsonProperty("format")
    val formats: List<String>?,
    @JsonProperty("title")
    val title: String?
): RdfResourceProvider {
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}