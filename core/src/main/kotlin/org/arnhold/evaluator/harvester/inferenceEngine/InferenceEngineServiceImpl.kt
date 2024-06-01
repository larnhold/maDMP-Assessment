package org.arnhold.evaluator.harvester.inferenceEngine

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.reasoner.Reasoner
import org.apache.jena.reasoner.ReasonerRegistry
import org.springframework.stereotype.Service

@Service
class InferenceEngineServiceImpl: InferenceEngineService {

    private val logger = KotlinLogging.logger {}

    override fun applyReasoning(ontology: OntModel, model: Model): Model {
        logger.info { "Reason over loaded DMP using DCS Ontology" }
        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(ontology)

        return ModelFactory.createInfModel(reasoner, model)
    }
}