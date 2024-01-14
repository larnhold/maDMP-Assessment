package org.arnhold.evaluator.dataProvision

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.evaluationManager.DMPLoaderParameters
import java.util.UUID

interface DataProviderService {

    fun getDCSOntology(): OntModel

    fun getDMPDQVOntology(): OntModel
    fun getExtensions(): Map<String, OntModel>

    fun getContextOntologies(): Map<String, OntModel>

    fun loadDMP(dmploader: String, dmpIdentifier: String): Model

    fun loadContextualizedDMP(parameters: DMPLoaderParameters): UUID

    fun getContextualizedDMP(id: UUID): Model

    fun updateStoredDMP(id: UUID, dmp: Model)

    fun saveModel(model: Model): UUID

}