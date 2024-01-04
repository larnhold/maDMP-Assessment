package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

data class License (
    @JsonProperty("license_ref")
    val licenseRef: String?,
    @JsonProperty("start_date")
    val startDate: String?,

): RdfResourceProvider {
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}