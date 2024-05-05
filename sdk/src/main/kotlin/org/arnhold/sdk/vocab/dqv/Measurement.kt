package org.arnhold.sdk.vocab.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

data class Measurement(
    val lifeCycleStage: DmpLifecycle,
    val isMeasurementOf: Metric,
    val guidance: Guidance?,
    val computedOn: DMPLocation,
    val value: Any,
    val softwareAgent: SoftwareAgent?,
    val testResults: List<TestResult> = ArrayList()
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DMPDQV.VALUE, value.toString())
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.APPLIED_AT_DMP_LIFECYCLE, lifeCycleStage),
            ObjectPropertyDefinition(DMPDQV.HAS_GUIDANCE, guidance, name, "guidance"),
            ObjectPropertyDefinition(DMPDQV.IS_MEASUREMENT_OF, isMeasurementOf),
            ObjectPropertyDefinition(DMPDQV.HAS_TEST_RESULT, testResults, name, ""),
            ObjectPropertyDefinition(DMPDQV.WAS_ATTRIBUTED_TO, softwareAgent),
            ObjectPropertyDefinition(DMPDQV.COMPUTED_ON, computedOn, name, "dmpLocation"),
        ), listOf())
    }
}
