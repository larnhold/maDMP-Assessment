package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.Measurement
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class FAIREvaluationEvaluator : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.FAIR_EVALUATION.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.FAIR_EVALUATION_DIMENSION,
            CategoryDimmensionModels.QUALITY_OF_ACTIONS_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}