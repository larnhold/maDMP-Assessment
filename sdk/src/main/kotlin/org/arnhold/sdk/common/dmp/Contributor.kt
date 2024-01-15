package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.ontologyDefinitions.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class Contributor(
    @JsonProperty("contributor_id")
    val contributorId: ContributorId?,
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
        ))
    }
}