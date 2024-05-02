package org.arnhold.evaluator.harvester.dataProvider

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.indicator.evaluationManager.DMPLoaderParameters
import java.util.UUID

interface DataProviderService {

    fun getDCSOntology(): OntModel

    fun getDMPDQVOntology(): OntModel

    fun getExtensions(): Map<String, OntModel>

    fun loadDMP(parameters: DMPLoaderParameters): UUID

    fun getDMP(id: UUID): Model

    fun saveModel(model: Model): UUID

}