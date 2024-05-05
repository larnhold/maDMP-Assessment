package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.stereotype.Component

@Component
class ExtensionCompletenessEvaluator : EvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.EXTENSION_COMPLETENESS.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.EXTENSION_COMPLETENESS_DIMENSION,
            CategoryDimmensionModels.COMPLETENSS_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(dmp: Model, context: List<DMPContext>, parameters: EvaluationTaskParameters): List<Measurement> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}