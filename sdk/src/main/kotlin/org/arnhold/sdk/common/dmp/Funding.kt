package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

data class Funding (
    @JsonProperty("funder_id")
    val funderId: FunderId?,
    @JsonProperty("funding_status")
    val fundingStatus: String?,
    @JsonProperty("grant_id")
    val grantId: GrantId?
): RdfResourceProvider {
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}