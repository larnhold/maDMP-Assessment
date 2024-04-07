package org.arnhold.sdk.common.dqv

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.RdfResourceProvider
import org.arnhold.sdk.common.rdf.parsing.ResourcePropertyDefinition
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

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
