package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class DCSComplianceMetricModels {

    companion object {
        val GUIDELINE_EXTRACTION_METRIC = Metric(
            "guideline_extraction_metric",
            "Extract information from the DMP or from some resource linked to the DMP to present to the human reviewer",
            "Guideline Extraction",
            null,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xstring.toString()
        )

        val COMPLEX_CONSTRAINT_METRIC = Metric(
            "guideline_complex_constraint_metric",
            "Indicate that a condition given in the guideline based on information contained in the DMP is correct according to a guideline",
            "Guideline Extraction",
            null,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )


        val DCS_WHITELIST_METRIC = Metric(
            "dcs_whitelist_metric",
            "Allowed values according to the DCS application profile",
            "DCS Whitelist",
            null,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val DCS_MULTIPLICITY_METRIC = Metric(
            "dcs_multiplicity_metric",
            "Mutltiplicity of value in the DMP in accordance with the DCS application profile",
            "DCS Multiplicity Compliance",
            null,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}