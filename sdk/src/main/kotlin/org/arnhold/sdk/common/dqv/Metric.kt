package org.arnhold.sdk.common.dqv

import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider
import org.arnhold.sdk.common.dmp.helper.ResourcePropertyDefinition
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

class Metric (
    val description: String,
    val title: String,
    val inDimension: Dimension,
    val applicableDMPLifeCycles: List<DmpLifecycle?>?,
    @JsonIgnore
    val expectedDataType: Resource,
    @JsonIgnore
    val usedVariables: List<Resource?>? = null,
    val metricTests: List<MetricTestDefinition?>? = null,
    val valueUpperBound: Any? = null
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title + "_Metric", listOf(
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.VALUE_UPPER_BOUND, valueUpperBound.toString())
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.IN_DIMENSION, inDimension, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_APPLICABLE_LIFECYCLE, applicableDMPLifeCycles, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_TEST_DEFINITION, metricTests, title, ""),
        ), listOf(
            ResourcePropertyDefinition(DMPDQV.EXPEXTED_DATA_TYPE, expectedDataType),
            ResourcePropertyDefinition(DMPDQV.USES, usedVariables)
        ))
    }
}
