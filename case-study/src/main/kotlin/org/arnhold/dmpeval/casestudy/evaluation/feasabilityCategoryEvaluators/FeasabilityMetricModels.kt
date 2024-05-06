package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import org.apache.jena.rdf.model.Resource
import org.apache.jena.vocabulary.XSD
import org.arnhold.sdk.model.CategoryDimmensionModels
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO

class FeasabilityMetricModels {
    companion object {
        val ID_AVAILABLE_METRIC = Metric(
            "id_available_metric",
            "Availability of an id",
            "IdAvailable",
            CategoryDimmensionModels.AVAILABILITY_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
            XSD.xboolean.toString()
        )

        fun getUriAvailableMetric(verb: Resource): Metric {
            return Metric(
                "uri_available_metric",
                "URI from ${verb.uri} Available",
                "${verb.uri.replace(DCSO.URI_PREFIX, "", true)}Available",
                CategoryDimmensionModels.AVAILABILITY_DIMENSION,
                listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
                XSD.xboolean.toString()
            )
        }
    }
}