package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class DCSCompletenessEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.DCS_COMPLETENESS.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.DCS_COMPLETENESS_DIMENSION,
            CategoryDimmensionModels.COMPLETENSS_CATEGORY
        )
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}