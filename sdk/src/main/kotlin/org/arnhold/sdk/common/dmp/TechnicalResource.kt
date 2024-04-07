package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.RdfResourceProvider

data class TechnicalResource (
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("name")
    val name: String?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.NAME, this.name)
        ), listOf())
    }
}