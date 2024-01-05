package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.DCSO

data class DmpId(
    @JsonProperty("identifier")
    val identifier: String?,
    @JsonProperty("type")
    val type: String?
): RdfResourceProvider {

    companion object {
        const val PREFIX = "https://w3id.org/dcso/ns/core#"
    }

    override fun toResource(model: Model, name: String): Resource {
        val dmpResource = model.createResource(String.format("%s%s", DMP.PREFIX, name))

        // data properties
        addDataProperty(dmpResource, DCSO.IDENTIFIER, identifier)
        addDataProperty(dmpResource, DCSO.TYPE, type)

        return dmpResource
    }

    private fun addDataProperty(subj: Resource, verb: Property, obj: String?) {
        obj?.let {
            subj.addProperty(verb, it)
        }
    }
}
