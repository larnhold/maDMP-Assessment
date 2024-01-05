package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

data class Host (
    @JsonProperty("availability")
    val availability: String?,
    @JsonProperty("backup_frequency")
    val backupFrequency: String?,
    @JsonProperty("backup_type")
    val backupTypes: String?,
    @JsonProperty("certified_with")
    val certifiedWith: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("geo_location")
    val geoLocaction: String?,
    @JsonProperty("pid_system")
    val pidSystems: List<String>?,
    @JsonProperty("storage_type")
    val storageType: String?,
    @JsonProperty("support_versioning")
    val supportVersioning: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("url")
    val url: String?
): RdfResourceProvider {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}