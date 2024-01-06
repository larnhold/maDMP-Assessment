package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class ContactID(
    @JsonProperty("identifier")
    val identifier:String?,
    @JsonProperty("type")
    val type: String?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.IDENTIFIER, identifier),
            DataPropertyDefinition(DCSO.TYPE, type),
        ), listOf())
    }
}
