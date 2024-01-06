package org.arnhold.dmpeval.casestudy.evaluation.fairGuidanceEvaluator

import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin
import org.springframework.stereotype.Component

@Component
class FairGuidanceEvaluation: EvaluationMethodPlugin {

    override fun suitableForDimension(dimension: Dimension): Boolean {
        TODO("Not yet implemented")
    }

    override fun suitableForMetric(metric: Metric): Boolean {
        TODO("Not yet implemented")
    }

    override fun getIdentifier(): String {
        TODO("Not yet implemented")
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}