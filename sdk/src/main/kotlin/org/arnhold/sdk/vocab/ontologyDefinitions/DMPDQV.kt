package org.arnhold.sdk.vocab.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DMPDQV {
    companion object {
        const val URI_PREFIX = "https://w3id.org/dmpdqv/ns/core#"
        const val DQV_PREFIX = "http://www.w3.org/ns/dqv#"
        const val TERMS_PREFIX = "http://purl.org/dc/terms/"
        const val PROV_PREFIX = "http://www.w3.org/ns/prov#"

        private val m = ModelFactory.createDefaultModel()

        val DESCRIPTION: Property = m.createProperty("${TERMS_PREFIX}description")
        val TITLE: Property = m.createProperty("${TERMS_PREFIX}title")
        val IDENTIFIER: Property = m.createProperty("${TERMS_PREFIX}identifier")
        val IN_CATEGORY: Property = m.createProperty("${DQV_PREFIX}inCategory")
        val IN_DIMENSION: Property = m.createProperty("${DQV_PREFIX}inDimension")
        val HAS_METRIC: Property = m.createProperty("${DQV_PREFIX}hasMetric")
        val EXPEXTED_DATA_TYPE: Property = m.createProperty("${DQV_PREFIX}expectedDataType")
        val HAS_APPLICABLE_LIFECYCLE: Property = m.createProperty("${URI_PREFIX}hasApplicableLifecycle")
        val APPLIED_AT_LIFECYCLE: Property = m.createProperty("${URI_PREFIX}appliedAtLifecycle")
        val VALUE: Property = m.createProperty("${DQV_PREFIX}value")
        val USES: Property = m.createProperty("${URI_PREFIX}uses")

        val IS_MEASUREMENT_OF: Property = m.createProperty("${DQV_PREFIX}isMeasurementOf")

        val HAS_PARENT_DIMENSION: Property = m.createProperty("${DQV_PREFIX}hasParentDimension")
        val HAS_SUB_DIMENSION: Property = m.createProperty("${DQV_PREFIX}hasSubDimension")

        val COMPUTED_ON: Property = m.createProperty("${DQV_PREFIX}computedOn")
        val ENTITY: Property = m.createProperty("${DQV_PREFIX}entity")
        val PROPERTY: Property = m.createProperty("${DQV_PREFIX}property")

        val APPLIED_AT_DMP_LIFECYCLE: Property = m.createProperty("${URI_PREFIX}appliedAtDMPLifecycle")
        val HAS_GUIDANCE: Property = m.createProperty("${URI_PREFIX}hasGuidance")
        val HAS_TEST_DEFINITION: Property = m.createProperty("${URI_PREFIX}hasTestDefinition")
        val HAS_TEST_RESULT: Property = m.createProperty("${URI_PREFIX}hasTestResult")
        val VALUE_UPPER_BOUND: Property = m.createProperty("${URI_PREFIX}valueUpperBound")
        val VALUE_LOWER_BOUND: Property = m.createProperty("${URI_PREFIX}valueLowerBound")
        val IN_METRIC_GROUP: Property = m.createProperty("${URI_PREFIX}inMetricGroup")

        val HAS_QUALITY_MEASUREMENT: Property = m.createProperty("${URI_PREFIX}hasQualityMeasurement")

        val WAS_ATTRIBUTED_TO: Property = m.createProperty("${PROV_PREFIX}wasAttributedTo")
    }
}
