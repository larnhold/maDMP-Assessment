package org.arnhold.sdk.vocab.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider

data class Funding (
    @JsonProperty("funder_id")
    val funderId: FunderId?,
    @JsonProperty("funding_status")
    val fundingStatus: String?,
    @JsonProperty("grant_id")
    val grantId: GrantId?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.FUNDING_STATUS, fundingStatus)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_GRANT_ID, grantId, name, "grantId"),
            ObjectPropertyDefinition(DCSO.HAS_FUNDER_ID, funderId, name, "funderId")
        ))
    }
}