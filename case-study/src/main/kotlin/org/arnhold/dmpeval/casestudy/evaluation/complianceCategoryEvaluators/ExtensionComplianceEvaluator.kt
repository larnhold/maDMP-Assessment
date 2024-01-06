package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class ExtensionComplianceEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.EXTENSION_COMPLIANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.EXTENSION_COMPLIANCE_DIMENSION,
            CategoryDimmensionModels.COMPLIANCE_CATEGORY
        )
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}