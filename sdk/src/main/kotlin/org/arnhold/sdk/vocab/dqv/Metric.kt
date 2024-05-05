package org.arnhold.sdk.vocab.dqv

import com.fasterxml.jackson.annotation.JsonInclude
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.tools.rdfParsing.DataPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.ObjectPropertyDefinition
import org.arnhold.sdk.tools.rdfParsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Metric (
    val identifier: String,
    var description: String,
    val title: String,
    val inDimension: Dimension,
    val applicableDMPLifeCycles: List<DmpLifecycle> = ArrayList(),
    val expectedDataType: String,
    var metricTests: MutableList<MetricTestDefinition> = ArrayList(),
    val valueUpperBound: Any? = null,
    val metricGroup: MetricGroup? = null,
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title + "_Metric", listOf(
            DataPropertyDefinition(DMPDQV.IDENTIFIER, identifier),
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
            DataPropertyDefinition(DMPDQV.TITLE, title),
            DataPropertyDefinition(DMPDQV.EXPEXTED_DATA_TYPE, expectedDataType),
            DataPropertyDefinition(DMPDQV.VALUE_UPPER_BOUND, valueUpperBound.toString())
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.IN_DIMENSION, inDimension, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_APPLICABLE_LIFECYCLE, applicableDMPLifeCycles, "", ""),
            ObjectPropertyDefinition(DMPDQV.HAS_TEST_DEFINITION, metricTests, title, ""),
            ObjectPropertyDefinition(DMPDQV.IN_METRIC_GROUP, metricGroup)
        ), listOf())
    }
}
