package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider

data class Cost(
    @JsonProperty("currency_code")
    val currencyCode: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("value")
    val value: Int?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.CURRENCY_CODE, currencyCode),
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.TITLE, title),
            DataPropertyDefinition(DCSO.VALUE, value.toString())
        ), listOf())
    }
}
