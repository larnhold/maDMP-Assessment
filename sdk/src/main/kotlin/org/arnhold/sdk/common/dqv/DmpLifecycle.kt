package org.arnhold.sdk.common.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.common.rdf.parsing.RdfResourceProvider
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

class DmpLifecycle(
    val title: DataLifecycle
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, "DMPLifecycle$title", listOf(
            DataPropertyDefinition(DMPDQV.TITLE, title.toString())
        ), listOf())
    }
}