package org.arnhold.sdk.vocab.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.vocab.rdf.parsing.DataPropertyDefinition
import org.arnhold.sdk.vocab.rdf.parsing.ObjectPropertyDefinition
import org.arnhold.sdk.vocab.rdf.parsing.RdfResourceProvider
import org.arnhold.sdk.vocab.ontologyDefinitions.DMPDQV

class TestResult (
    val testDefinition: MetricTestDefinition,
    val value: Any
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, "${name}_${testDefinition.title}",
            listOf(
                DataPropertyDefinition(DMPDQV.VALUE, value.toString())
            ),
            listOf(
                ObjectPropertyDefinition(DMPDQV.HAS_TEST_DEFINITION, testDefinition)
            ),
            listOf())
    }
}
