package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels.Companion.DCS_COMPLETENESS_DIMENSION
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class DCS_CompletenessMetricModels {
    companion object {
        val REQUIRED_ENTITY_EXISTENT_METRIC = Metric(
            "required_entity_existent",
            "Existence of a required entity according to the specification",
            "DCS Completeness",
            DCS_COMPLETENESS_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val REQUIRED_PROPERTY_EXISTENT_METRIC = Metric(
            "required_property_existent",
            "Existence of a required property according to the specification",
            "DCS Completeness",
            DCS_COMPLETENESS_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val REQUIRED_ENTITY_OR_PROPERTY_EXISTENT_METRIC = Metric(
            "required_entity_or_property_existent",
            "Existence of a required entity or property according to the specification",
            "DCS Completeness",
            DCS_COMPLETENESS_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}