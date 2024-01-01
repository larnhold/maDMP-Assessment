package org.arnhold.sdk.dmpLoader

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.plugin.ConfigurablePlugin
import org.springframework.plugin.core.Plugin
import java.io.File
import java.util.Properties

interface DmpLoaderPlugin: ConfigurablePlugin<String> {
    fun loadDMP(identifier: String, dcsOntology: OntModel): Model
}