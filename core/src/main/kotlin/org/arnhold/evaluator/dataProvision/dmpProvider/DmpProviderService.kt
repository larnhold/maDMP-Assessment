package org.arnhold.evaluator.dataProvision.dmpProvider

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model

interface DmpProviderService {

    fun loadDMP(dmploader: String, dmpIdentifier: String, dcsOntology: OntModel): Model

}