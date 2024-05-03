package org.arnhold.evaluator.indicator.evaluationProvider

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Measurement

interface EvaluationProviderService {
    fun getAllEvaluators(): List<EvaluatorPlugin>
    suspend fun produceAllMeasurements(dmp: Model, context: List<DMPContext>, lifecycle: DataLifecycle): List<Measurement>
}