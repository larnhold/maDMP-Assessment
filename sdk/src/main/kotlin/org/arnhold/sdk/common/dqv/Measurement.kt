package org.arnhold.sdk.common.dqv

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider
import org.arnhold.sdk.common.dmp.helper.ResourcePropertyDefinition
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

data class Measurement(
    val lifeCycleStage: DmpLifecycle,
    val isMeasurementOf: Metric,
    val guidance: Guidance,
    @JsonIgnore
    val computedOn: Resource,
    val value: Any
): RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DMPDQV.VALUE, value.toString())
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.APPLIED_AT_DMP_lifecycle, lifeCycleStage, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_GUIDANCE, guidance, name, "guidance"),
            ObjectPropertyDefinition(DMPDQV.IS_MEASUREMENT_OF, isMeasurementOf, "", ""),
        ), listOf(
            ResourcePropertyDefinition(DMPDQV.COMPUTED_ON, computedOn),
        ))
    }
}
