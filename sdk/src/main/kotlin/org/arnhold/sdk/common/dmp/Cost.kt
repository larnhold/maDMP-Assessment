package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

data class Cost(
    @JsonProperty("currency_code")
    val currencyCode: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("value")
    val value: Int?
): RdfResourceProvider {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}
