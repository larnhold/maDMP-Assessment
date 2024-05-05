package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.tools.shacl.ShaclValidationService
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.DMPLocation
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Measurement
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

        fun getDCSWhitelistConformMeasurement(lifecycle: DmpLifecycle, computedOn: DMPLocation): Measurement {
            return Measurement(
                lifecycle,
                this.DCS_WHITELIST_METRIC,
                null,
                computedOn,
                true,
                ShaclValidationService.SHACL_AGENT,
                ArrayList()
            )
        }
    }
}