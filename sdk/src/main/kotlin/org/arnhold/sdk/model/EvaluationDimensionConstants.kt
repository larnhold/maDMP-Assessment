package org.arnhold.sdk.model

enum class EvaluationDimensionConstants(title: String) {
    DCS_COMPLETENESS("dcs_completeness"),
    // TODO make subdimension of guidleine cmplaince
    SCIENCE_EUROPE_EXTENSION_COMPLETENESS("science_europe_extension_completeness"),
    ACCURACY("accuracy"),
    AVAILABILITY("availability"),
    CONSISTENCY("consistency"),
    FAIR("FAIR"),
    FINDABLE("findable"),
    ACCESSIBLE("accessible"),
    INTEROPERABLE("interoperable"),
    REUSABLE("reusable"),
    GUIDELINE_COMPLIANCE("guideline_compliance"),
    DCS_COMPLIANCE("dcs_compliance"),
    SCIENCE_EUROPE_COMPLIANCE("extension_compliance")
}