package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

data class Contact (
    @JsonProperty("contact_id")
    val contactId: ContactID?,
    @JsonProperty("mbox")
    val mbox: String?,
    @JsonProperty("name")
    val name: String?
): RdfResourceProvider {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}