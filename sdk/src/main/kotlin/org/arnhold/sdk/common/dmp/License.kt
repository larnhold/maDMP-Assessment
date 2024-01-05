package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider
import javax.xml.crypto.Data

data class License (
    @JsonProperty("license_ref")
    val licenseRef: String?,
    @JsonProperty("start_date")
    val startDate: String?,

): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.LICENSE_REF, licenseRef),
            DataPropertyDefinition(DCSO.START_DATE, startDate)
        ), listOf())
    }
}