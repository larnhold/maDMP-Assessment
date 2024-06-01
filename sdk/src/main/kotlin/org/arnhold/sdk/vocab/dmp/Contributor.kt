package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.tools.rdfParsing.ResourcePropertyDefinition
import org.arnhold.sdk.vocab.ontologyDefinitions.RDF

data class Contributor(
    @JsonProperty("contributor_id")
    val contributorId: Id?,
    @JsonProperty("mbox")
    val mbox: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("role")
    val roles: List<String?>?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.MBOX, mbox),
            DataPropertyDefinition(DCSO.NAME, this.name),
            DataPropertyDefinition(DCSO.ROLE, roles)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_CONTRIBUTOR_ID, contributorId, name, "contributorId")
        ), listOf(
            ResourcePropertyDefinition(RDF.TYPE, model.createResource("https://w3id.org/dcso/ns/core#Contributor"))
        ))
    }
}