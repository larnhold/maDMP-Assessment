package org.arnhold.sdk.common.ontologyDefinitions

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
        val IN_CATEGORY: Property = m.createProperty("${DQV_PREFIX}inCategory")
        val IN_DIMENSION: Property = m.createProperty("${DQV_PREFIX}inDimension")
        val EXPEXTED_DATA_TYPE: Property = m.createProperty("${DQV_PREFIX}expectedDataType")
        val HAS_APPLICABLE_DMP_LIFECYCLE: Property = m.createProperty("${URI_PREFIX}hasApplicableDMPLifecycle")
        val VALUE: Property = m.createProperty("${DQV_PREFIX}value")
        val USED_VARIABLE: Property = m.createProperty("${URI_PREFIX}usedVariable")

        val IS_MEASUREMENT_OF: Property = m.createProperty("${DQV_PREFIX}isMeasurementOf")

        val DERIVED_FROM: Property = m.createProperty("${DQV_PREFIX}derivedFrom")

        val COMPUTED_ON: Property = m.createProperty("${DQV_PREFIX}computedOn")
        val APPLIED_AT_DMP_lifecycle: Property = m.createProperty("${URI_PREFIX}appliedAtDMPLifecycle")
        val HAS_GUIDANCE: Property = m.createProperty("${URI_PREFIX}hasGuidance")
        val HAS_TEST_DEFINITION: Property = m.createProperty("${URI_PREFIX}hasTestDefinition")
        val HAS_TEST_RESULT: Property = m.createProperty("${URI_PREFIX}hasTestResult")
        val EXPECTED_VALUE: Property = m.createProperty("${URI_PREFIX}expectedValue")

        val HAS_QUALITY_MEASUREMENT: Property = m.createProperty("${URI_PREFIX}hasQualityMeasurement")

        val WAS_ATTRIBUTED_TO: Property = m.createProperty("${PROV_PREFIX}wasAttributedTo")
    }
}
