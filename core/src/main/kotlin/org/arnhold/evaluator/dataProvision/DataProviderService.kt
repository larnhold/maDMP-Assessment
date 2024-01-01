package org.arnhold.evaluator.dataProvision

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.openAire.model.schema.AlternateIdentifier

interface DataProviderService {

    fun getDCSOntology(): OntModel
    fun getExtensions(): Map<String, OntModel>

    fun getContextOntologies(): Map<String, OntModel>

    fun loadDMP(dmploader: String, dmpIdentifier: String): Model

}