package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.vocab.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.vocab.rdf.parsing.ObjectPropertyDefinition
import org.arnhold.sdk.vocab.rdf.parsing.RdfResourceProvider

data class Project (
    @JsonProperty("funding")
    val fundings: List<Funding>?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("end")
    val end: String?,
    @JsonProperty("start")
    val start: String?,
    @JsonProperty("title")
    val title: String?
) : RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.END, end),
            DataPropertyDefinition(DCSO.START, start),
            DataPropertyDefinition(DCSO.TITLE, title)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_FUNDING, fundings, name, "funding")
        ))
    }
}