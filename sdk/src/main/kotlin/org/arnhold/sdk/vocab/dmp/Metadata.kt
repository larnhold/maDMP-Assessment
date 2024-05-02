package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider

data class Metadata (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("language")
    val language: String?,
    @JsonProperty("metadata_standard_id")
    val metadataStandardId: MetadataStandardId?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.LANGUAGE, language)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_METADATA_STANDARD_ID, metadataStandardId, name, "metadataStandardId")
        ))
    }
}