package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class AccuracyEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.ACCURACY.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.ACCURACY_DIMENSION,
            CategoryDimmensionModels.FEASABILITY_CATEGORY
        )
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}