package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import org.apache.jena.rdf.model.Resource
import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.DmpLifecycle
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.common.ontologyDefinitions.DCSO

class FeasabilityMetricModels {
    companion object {
        val ID_AVAILABLE_METRIC = Metric(
            "Availability of an id",
            "IdAvailable",
            CategoryDimmensionModels.AVAILABILITY_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
            XSD.xboolean,
        )

        fun getUriAvailableMetric(verb: Resource): Metric {
            return Metric(
                "URI from ${verb.uri} Available",
                "${verb.uri.replace(DCSO.URI_PREFIX, "", true)}Available",
                CategoryDimmensionModels.AVAILABILITY_DIMENSION,
                listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
                XSD.xboolean,
                listOf(verb)
            )
        }
    }
}