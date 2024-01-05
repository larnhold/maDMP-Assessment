package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.apache.jena.vocabulary.DCAT
import org.arnhold.sdk.common.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class Contact (
    @JsonProperty("contact_id")
    val contactId: ContactID?,
    @JsonProperty("mbox")
    val mbox: String?,
    @JsonProperty("name")
    val name: String?
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.MBOX, mbox),
            DataPropertyDefinition(DCSO.NAME, this.name)
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_CONTACT_ID, contactId, name,  "contactId")
        ))
    }
}