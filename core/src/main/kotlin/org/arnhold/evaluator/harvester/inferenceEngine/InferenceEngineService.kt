package org.arnhold.evaluator.harvester.inferenceEngine

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model

interface InferenceEngineService {
    fun applyReasoning(ontology: OntModel, model: Model): Model
}