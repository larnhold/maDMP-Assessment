package org.arnhold.dmpeval.casestudy.evaluation.constraintEvaluator

import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin
import org.springframework.stereotype.Component

@Component
class ConstraintEvaluation: EvaluationMethodPlugin {

    override fun getIdentifier(): String {
        TODO("Not yet implemented")
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        TODO("Not yet implemented")
    }

    override fun supports(p0: Metric): Boolean {
        TODO("Not yet implemented")
    }
}