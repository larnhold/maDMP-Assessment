package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.tools.rdfParsing.ResourcePropertyDefinition
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSX
import org.arnhold.sdk.vocab.ontologyDefinitions.RDF
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
    val title: String?,
    @JsonProperty("restrictionExplanation")
    val restrictionExplanation: String?,
    @JsonProperty("propertyRightsExplanation")
    val propertyRightsExplanation: PropertyRightsExplanation?
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
            DataPropertyDefinition(DCSO.TITLE, title),
            DataPropertyDefinition(DCSX.RESTRICTION_EXPLANATION, restrictionExplanation),
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_LICENSE, license, name, "license"),
            ObjectPropertyDefinition(DCSO.HAS_HOST, host, name, "host"),
            ObjectPropertyDefinition(DCSX.HAS_PROPERTY_RIGHTS_EXPLANATION, propertyRightsExplanation, name, "propertyRightsExplanation"),
        ), listOf(
            ResourcePropertyDefinition(RDF.TYPE, model.createResource("https://w3id.org/dcso/ns/core#Distribution"))
        ))
    }
}