package org.arnhold.evaluator.indicator.evaluationProvider

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.Measurement

interface EvaluationProviderService {
    fun getAllEvaluators(): List<DimensionEvaluatorPlugin>
    suspend fun produceAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement>
}