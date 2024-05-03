package org.arnhold.sdk.evaluator

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.plugin.ConfigurablePlugin
import org.arnhold.sdk.vocab.context.DMPContext

interface EvaluatorPlugin: ConfigurablePlugin<String, EvaluatorInformation> {
    fun getAllMeasurements(dmp: Model, context: List<DMPContext>, lifecycle: DataLifecycle): List<Measurement>
}