package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class ExtensionCompletenessEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.EXTENSION_COMPLETENESS.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.EXTENSION_COMPLETENESS_DIMENSION,
            CategoryDimmensionModels.COMPLETENSS_CATEGORY
        )
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}