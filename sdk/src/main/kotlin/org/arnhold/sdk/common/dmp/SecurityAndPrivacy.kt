package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.RdfResourceProvider

data class SecurityAndPrivacy (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("title")
    val title: String?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.TITLE, title)
        ), listOf())
    }
}