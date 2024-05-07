package org.arnhold.sdk.evaluator

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.plugin.ConfigurablePlugin
import org.arnhold.sdk.vocab.context.DMPContext

interface EvaluatorPlugin: ConfigurablePlugin<String, EvaluatorInformation> {
    fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<String, OntModel>
    ): List<Measurement>
}