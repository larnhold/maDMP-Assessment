package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class Host (
    @JsonProperty("availability")
    val availability: String?,
    @JsonProperty("backup_frequency")
    val backupFrequency: String?,
    @JsonProperty("backup_type")
    val backupType: String?,
    @JsonProperty("certified_with")
    val certifiedWith: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("geo_location")
    val geoLocaction: String?,
    @JsonProperty("pid_system")
    val pidSystem: List<String>?,
    @JsonProperty("storage_type")
    val storageType: String?,
    @JsonProperty("support_versioning")
    val supportVersioning: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("url")
    val url: String?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.AVAILABILITY, availability),
            DataPropertyDefinition(DCSO.BACKUP_FREQUENCY, backupFrequency),
            DataPropertyDefinition(DCSO.BACKUP_TYPE, backupType),
            DataPropertyDefinition(DCSO.CERTIFIED_WITH, certifiedWith),
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.GEOLOCATION, geoLocaction),
            DataPropertyDefinition(DCSO.PID_SYSTEM, pidSystem),
            DataPropertyDefinition(DCSO.STORAGE_TYPE, storageType),
            DataPropertyDefinition(DCSO.SUPPORT_VERSIONING, supportVersioning),
            DataPropertyDefinition(DCSO.TITLE, title),
            DataPropertyDefinition(DCSO.URL, url)
        ), listOf())
    }
}