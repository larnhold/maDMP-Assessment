package org.arnhold.evaluator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Measurement


interface MetricProcessingService {
    fun <T> produceMeasurementsForDimension(dmp: Model, dimension: Dimension): List<Measurement<T>>
}