package org.arnhold.sdk.vocab.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class RDF {
    companion object {
        const val URI_PREFIX = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"

        private val m = ModelFactory.createDefaultModel()


        val TYPE: Property = m.createProperty(String.format("%stype", URI_PREFIX))
    }
}