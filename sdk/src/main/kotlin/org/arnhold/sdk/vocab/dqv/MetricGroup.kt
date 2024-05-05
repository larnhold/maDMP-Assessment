package org.arnhold.sdk.vocab.dqv

import com.fasterxml.jackson.annotation.JsonInclude
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MetricGroup (
    val identifier: String,
    val title: String?,
    val description: String?
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DMPDQV.IDENTIFIER, identifier),
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description)
        ), listOf())
    }
}