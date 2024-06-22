package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels.Companion.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE_DIMENSION
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class ScienceEuropeComplianceMetricModels {

    //M14

    companion object {
        val SCIENCE_EUROPE_GUIDELINE_VERIFICATION_METRIC = Metric(
            "science_europe_guideline_verification_metric",
            "Indicates if a guideline of the Science Europe DMP Evaluation Rubric is fulfilled or not.",
            "Science Europe Guideline Verification",
            SCIENCE_EUROPE_GUIDELINE_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}