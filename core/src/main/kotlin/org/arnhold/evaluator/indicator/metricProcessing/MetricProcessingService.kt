package org.arnhold.evaluator.indicator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.Dimension
import org.arnhold.sdk.vocab.dqv.Measurement


interface MetricProcessingService {
    fun produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement>
    suspend fun produceAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement>
}