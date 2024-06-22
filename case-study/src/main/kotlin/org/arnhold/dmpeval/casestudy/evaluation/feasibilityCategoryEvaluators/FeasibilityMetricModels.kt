package org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class FeasibilityMetricModels {
    companion object {
        val LINKED_RESOURCE_EXISTENCE_METRIC = Metric(
            "existence_of_linked_resource_metric",
            "Availability of a linked resource",
            "Existence of linked resource",
            CategoryDimmensionModels.AVAILABILITY_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
            XSD.xboolean.toString()
        )

        val PROPERTY_MATCHES_GROUND_TRUTH_METRIC = Metric(
            "property_matches_ground_truth_metric",
            "Indicate that the value of a property is correct if there is some reference data that can be assumed to be true",
            "Property matches Ground Truth",
            CategoryDimmensionModels.ACCURACY_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
            XSD.xboolean.toString()
        )
    }
}