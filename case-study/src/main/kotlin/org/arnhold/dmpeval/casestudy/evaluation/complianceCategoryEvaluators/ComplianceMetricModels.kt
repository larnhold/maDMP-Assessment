package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.sdk.model.CategoryDimmensionModels
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class ComplianceMetricModels {
    companion object {
        val DCS_WHITELIST_METRIC = Metric(
            "dcs_whitelist_metric",
            "Allowed values according to the DCS application profile",
            "DCS Whitelist",
            CategoryDimmensionModels.DCS_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val DCS_MULTIPLICITY_METRIC = Metric(
            "dcs_multiplicity_metric",
            "Mutltiplicity of value in the DMP in accordance with the DCS application profile",
            "DCS Multiplicity Compliance",
            CategoryDimmensionModels.DCS_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}