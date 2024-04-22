package org.arnhold.sdk.vocab.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.vocab.rdf.parsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

class DmpLifecycle(
    val title: DataLifecycle
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, "DMPLifecycle$title", listOf(
            DataPropertyDefinition(DMPDQV.TITLE, title.toString())
        ), listOf())
    }
}