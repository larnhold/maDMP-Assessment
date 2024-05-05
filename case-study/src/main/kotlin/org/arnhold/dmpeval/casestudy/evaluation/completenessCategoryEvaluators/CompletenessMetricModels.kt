package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class CompletenessMetricModels {
    companion object {
        val DCS_COMPLETENESS_METRIC = Metric(
            "dcs_completeness_metric",
            "Existence of all required properties according to the DCS application profile",
            "DCS Completeness",
            CategoryDimmensionModels.DCS_COMPLETENESS_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}