package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSX

data class PropertyRightsExplanation (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("propertyRightsExplanationId")
    val propertyRightsExplanationId: Id?
) : RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
        ), listOf(
            ObjectPropertyDefinition(DCSX.HAS_PROPERTY_RIGHTS_EXPLANATION_ID, propertyRightsExplanationId,  name, "propertyRightsExplanationId")
        ))
    }
}