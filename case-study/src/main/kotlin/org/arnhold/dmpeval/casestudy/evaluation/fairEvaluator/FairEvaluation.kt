package org.arnhold.dmpeval.casestudy.evaluation.fairEvaluator

import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.springframework.stereotype.Component

@Component
class FairEvaluation: EvaluationMethodPlugin {

    override fun suitableForDimension(dimension: Dimension): Boolean {
        TODO("Not yet implemented")
    }

    override fun suitableForMetric(metric: Metric): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPluginIdentifier(): String {
        TODO("Not yet implemented")
    }

    override fun getPluginInformation(): EvaluatorInformation {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}