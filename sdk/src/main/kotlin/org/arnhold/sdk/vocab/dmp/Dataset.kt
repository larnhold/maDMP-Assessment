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

data class Dataset (
    @JsonProperty("distribution")
    val distributions: List<Distribution>?,
    @JsonProperty("security_and_privacy")
    val securityAndPrivacy: List<SecurityAndPrivacy>?,
    @JsonProperty("technical_resource")
    val technicalResource: List<TechnicalResource>?,
    @JsonProperty("metadata")
    val metadata: List<Metadata>?,
    @JsonProperty("data_quality_assurance")
    val dataQualityAssurance: List<String>?,
    @JsonProperty("dataset_id")
    val datasetId: Id?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("issued")
    val issued: String?,
    @JsonProperty("keyword")
    val keyword: List<String>?,
    @JsonProperty("language")
    val language: String?,
    @JsonProperty("personal_data")
    val personalData: String?,
    @JsonProperty("preservation_statement")
    val preservationStatement: String?,
    @JsonProperty("sensitive_data")
    val sensitiveData: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("type")
    val type: String?,
    @JsonProperty("isReused")
    val isReused: String?,
    @JsonProperty("targetAudience")
    val targetAudience: String?,
    @JsonProperty("methodology")
    val methodology: Methodology?,
    @JsonProperty("dataQualityAssuranceResource")
    val dataQualityAssuranceResource: DataQualityAssuranceResource?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DATA_QUALITY_ASSURANCE, dataQualityAssurance),
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.ISSUED, issued),
            DataPropertyDefinition(DCSO.KEYWORD, keyword),
            DataPropertyDefinition(DCSO.LANGUAGE, language),
            DataPropertyDefinition(DCSO.PERSONAL_DATA, personalData),
            DataPropertyDefinition(DCSO.PRESERVATION_STATEMENT, preservationStatement),
            DataPropertyDefinition(DCSO.SENSITIVE_DATA, sensitiveData),
            DataPropertyDefinition(DCSO.TITLE, title),
            DataPropertyDefinition(DCSO.DATASET_TYPE, type),
            DataPropertyDefinition(DCSX.IS_REUSED, isReused),
            DataPropertyDefinition(DCSX.TARGET_AUDIENCE, targetAudience),
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_DISTRIBUTION, distributions, name, "distribution"),
            ObjectPropertyDefinition(DCSO.HAS_SECURITY_AND_PRIVACY, securityAndPrivacy, name, "securityAndPrivacy"),
            ObjectPropertyDefinition(DCSO.HAS_TECHNICAL_RESOURCE, technicalResource, name, "technicalResource"),
            ObjectPropertyDefinition(DCSO.HAS_METADATA, metadata, name, "metadata"),
            ObjectPropertyDefinition(DCSO.HAS_DATASET_ID, datasetId, name, "datasetId"),
            ObjectPropertyDefinition(DCSX.HAS_METHODOLOGY, methodology, name, "methodology"),
            ObjectPropertyDefinition(DCSX.HAS_DATA_QUALIY_ASSURANCE_RESOURCE, dataQualityAssuranceResource, name, "dataQualityAssuranceResource")
        ), listOf(
            ResourcePropertyDefinition(RDF.TYPE, model.createResource("https://w3id.org/dcso/ns/core#Dataset"))
        ))
    }
}