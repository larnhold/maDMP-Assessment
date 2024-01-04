package org.arnhold.evaluator.metricProcessing

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.common.dqv.Measurement


interface MetricProcessingService {
    fun produceMeasurements(dmp: Model): List<Measurement>
}