package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels.Companion.DCS_COMPLIANCE_DIMENSION
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric

class DCSComplianceMetricModels {

    companion object {
        val DCS_WHITELIST_METRIC = Metric(
            "dcs_whitelist_metric",
            "Allowed values according to the DCS application profile",
            "DCS Whitelist",
            DCS_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val DCS_VALUE_PATTERN_CONTRAINT_METRIC = Metric(
            "dcs_value_pattern_compliance_metric",
            "Restrictions on pattern of values according to the DCS application profile",
            "DCS Value Pattern Constraint",
            DCS_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )

        val DCS_MULTIPLICITY_METRIC = Metric(
            "dcs_multiplicity_metric",
            "Mutltiplicity of value in the DMP in accordance with the DCS application profile",
            "DCS Multiplicity Compliance",
            DCS_COMPLIANCE_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PLANNING)),
            XSD.xboolean.toString()
        )
    }
}