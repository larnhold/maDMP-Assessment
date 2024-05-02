package org.arnhold.sdk.vocab.dqv

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.tools.rdfParsing.ResourcePropertyDefinition
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

class MetricTestDefinition (
    val title: String,
    val description: String,
    val expectedValue: Any,
    @JsonIgnore
    val expectedDataType: Resource,
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title, listOf(
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
            DataPropertyDefinition(DMPDQV.VALUE_UPPER_BOUND, expectedValue.toString())
        ), listOf(), listOf(
            ResourcePropertyDefinition(DMPDQV.EXPEXTED_DATA_TYPE, expectedDataType),
        ))
    }
}
