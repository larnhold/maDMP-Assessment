package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.ObjectPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.RdfResourceProvider
import java.math.BigInteger

data class Distribution (
    @JsonProperty("license")
    val license: List<License>?,
    @JsonProperty("host")
    val host: Host?,
    @JsonProperty("access_url")
    val accessUrl: String?,
    @JsonProperty("available_until")
    val availableUntil: String?,
    @JsonProperty("byte_size")
    val byteSize: BigInteger?,
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
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.ACCESS_URL, accessUrl),
            DataPropertyDefinition(DCSO.AVAILABLE_UNTIL, availableUntil),
            DataPropertyDefinition(DCSO.BYTE_SIZE, byteSize.toString()),
            DataPropertyDefinition(DCSO.DATA_ACCESS, dataAccess),
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.DOWNLOAD_URL, downloadUrl),
            DataPropertyDefinition(DCSO.FORMAT, formats),
            DataPropertyDefinition(DCSO.TITLE, title)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_LICENSE, license, name, "license"),
            ObjectPropertyDefinition(DCSO.HAS_HOST, host, name, "host")
        ))
    }
}