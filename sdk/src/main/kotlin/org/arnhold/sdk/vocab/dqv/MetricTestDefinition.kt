package org.arnhold.sdk.vocab.dqv

import com.fasterxml.jackson.annotation.JsonInclude
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

@JsonInclude(JsonInclude.Include.NON_NULL)
class MetricTestDefinition (
    val identifier: String,
    val title: String,
    val description: String,
    val expectedValue: Any? = null,
    val expectedDataType: String,
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title, listOf(
            DataPropertyDefinition(DMPDQV.IDENTIFIER, identifier),
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
            DataPropertyDefinition(DMPDQV.VALUE_UPPER_BOUND, expectedValue.toString()),
            DataPropertyDefinition(DMPDQV.EXPEXTED_DATA_TYPE, expectedDataType),
        ), listOf(), listOf())
    }
}
