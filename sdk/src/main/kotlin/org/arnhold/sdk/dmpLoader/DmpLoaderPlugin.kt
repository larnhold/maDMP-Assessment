package org.arnhold.sdk.dmpLoader

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.plugin.ConfigurablePlugin

interface DmpLoaderPlugin: ConfigurablePlugin<String, DmpLoaderInformation> {
    fun loadDMP(identifier: String, dcsOntology: OntModel): Model
}