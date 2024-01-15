package org.arnhold.sdk.common.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DMPDQV {
    companion object {
        const val URI_PREFIX = "https://w3id.org/dmpdqv/ns/core#"
        const val DQV_PREFIX = "http://www.w3.org/ns/dqv#"
        const val TERMS_PREFIX = "http://purl.org/dc/terms/"

        private val m = ModelFactory.createDefaultModel()

        val DESCRIPTION: Property = m.createProperty("${TERMS_PREFIX}description")
        val TITLE: Property = m.createProperty("${TERMS_PREFIX}title")
        val IN_CATEGORY: Property = m.createProperty("${DQV_PREFIX}inCategory")
        val IN_DIMENSION: Property = m.createProperty("${DQV_PREFIX}inDimension")
        val EXPEXTED_DATA_TYPE: Property = m.createProperty("${DQV_PREFIX}expectedDataType")
        val HAS_APPLICABLE_DMP_LIFECYCLE: Property = m.createProperty("${URI_PREFIX}hasApplicableDMPLifecycle")
        val VALUE: Property = m.createProperty("${DQV_PREFIX}value")
        val METRIC_TYPE: Property = m.createProperty("${DQV_PREFIX}metricType")

        val IS_MEASUREMENT_OF: Property = m.createProperty("${DQV_PREFIX}isMeasurementOf")
        val IS_MEASUREMENT_FOR: Property = m.createProperty("${URI_PREFIX}measurementFor")

        val COMPUTED_ON: Property = m.createProperty("${DQV_PREFIX}computedOn")
        val APPLIED_AT_DMP_lifecycle: Property = m.createProperty("${URI_PREFIX}appliedAtDMPLifecycle")
        val HAS_GUIDANCE: Property = m.createProperty("${URI_PREFIX}hasGuidance")

        val HAS_QUALITY_MEASUREMENT: Property = m.createProperty("${URI_PREFIX}hasQualityMeasurement")
    }
}
