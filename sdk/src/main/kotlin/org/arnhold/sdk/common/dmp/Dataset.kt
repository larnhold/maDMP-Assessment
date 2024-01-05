package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

data class Dataset (
    @JsonProperty("distribution")
    val distributions: List<Distribution>?,
    @JsonProperty("security_and_privacy")
    val securityAndPrivacies: List<SecurityAndPrivacy>?,
    @JsonProperty("technical_resource")
    val technicalResources: List<TechnicalResource>?,
    @JsonProperty("metadata")
    val metadata: List<Metadata>?,
    @JsonProperty("data_quality_assurance")
    val dataQualityAssurances: List<String>?,
    @JsonProperty("dataset_id")
    val datasetId: DatasetId?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("issued")
    val issued: String?,
    @JsonProperty("keyword")
    val keywords: List<String>?,
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
    val type: String?
): RdfResourceProvider {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}