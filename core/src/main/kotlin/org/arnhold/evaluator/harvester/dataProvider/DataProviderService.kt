package org.arnhold.evaluator.harvester.dataProvider

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.indicator.evaluationManager.DMPLoaderParameters
import org.arnhold.sdk.vocab.context.DMPContext
import java.util.UUID

interface DataProviderService {

    fun getDCSOntology(): OntModel

    fun getDMPDQVOntology(): OntModel

    fun getExtensions(): Map<String, OntModel>

    fun loadContext(model: Model): List<DMPContext>

    fun loadDMP(parameters: DMPLoaderParameters): UUID

    fun getDMP(id: UUID): Model

    fun saveModel(model: Model): UUID

    //TODO save as json
    fun saveAsJson()

}