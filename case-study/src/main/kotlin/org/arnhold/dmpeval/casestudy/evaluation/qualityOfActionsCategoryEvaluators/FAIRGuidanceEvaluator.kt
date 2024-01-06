package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class FAIRGuidanceEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.FAIR_GUIDANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.FAIR_GUIDANCE_DIMENSION,
            CategoryDimmensionModels.QUALITY_OF_ACTIONS_CATEGORY
        )
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}