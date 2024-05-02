package org.arnhold.sdk.vocab.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.tools.rdfParsing.ResourcePropertyDefinition
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

data class DMPLocation(
    val identifier: Resource?,
    val entity: Resource?,
    val property: Resource?
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
        ), listOf(), listOf(
            ResourcePropertyDefinition(DMPDQV.IDENTIFIER, identifier),
            ResourcePropertyDefinition(DMPDQV.ENTITY, entity),
            ResourcePropertyDefinition(DMPDQV.PROPERTY, property)
        ))
    }
}