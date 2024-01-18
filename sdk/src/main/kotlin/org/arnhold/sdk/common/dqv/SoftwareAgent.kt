package org.arnhold.sdk.common.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

data class SoftwareAgent(
    var title: String,
    var description: String
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title, listOf(
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
        ), listOf(), listOf())
    }
}
