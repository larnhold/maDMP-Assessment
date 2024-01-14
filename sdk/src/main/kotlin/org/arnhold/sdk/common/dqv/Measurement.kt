package org.arnhold.sdk.common.dqv

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.constants.MetricType
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

    private fun getTypeDependentMeasurementOf(): ObjectPropertyDefinition {
        return when (isMeasurementOf.metricType) {
            MetricType.MEASURING -> ObjectPropertyDefinition(DMPDQV.IS_MEASURING_MEASUREMENT_OF, isMeasurementOf, "", "")
            MetricType.CHECKING -> ObjectPropertyDefinition(DMPDQV.IS_CHECKING_MEASUREMENT_OF, isMeasurementOf, "", "")
            MetricType.EXTRACTING ->ObjectPropertyDefinition(DMPDQV.IS_EXTRACTING_MEASUREMENT_OF, isMeasurementOf, "", "")
            MetricType.SCORING -> ObjectPropertyDefinition(DMPDQV.IS_SCORING_MEASUREMENT_OF, isMeasurementOf, "", "")
            else -> {
                ObjectPropertyDefinition(DMPDQV.IS_MEASUREMENT_OF, isMeasurementOf, "", "")
            }
        }
    }

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DMPDQV.VALUE, value.toString())
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.APPLIED_AT_DMP_lifecycle, lifeCycleStage, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_GUIDANCE, guidance, name, "guidance"),
            getTypeDependentMeasurementOf()
        ), listOf(
            ResourcePropertyDefinition(DMPDQV.COMPUTED_ON, computedOn)
        ))
    }
}
