package org.arnhold.evaluator.indicator

import org.arnhold.sdk.model.DMPLoaderParameters
import org.arnhold.evaluator.indicator.evaluationManager.EvaluationManagerService
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.model.EvaluationTaskResult
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/evaluation/facade")
class CaseStudyFacadeController @Autowired constructor(
    val evaluationManagerService: EvaluationManagerService
) {

    @PostMapping("evaluate")
    fun createEvaluation(): EvaluationTaskResult {
        val dmploader = DMPLoaderParameters("", "zenodo/12.json")

        val parameters =  EvaluationTaskParameters(
            dmpLoaderParameters = dmploader,
            categories = null,
            dataLifecycle = DataLifecycle.PLANNING,
            dimensions = null
        )

        return evaluationManagerService.createEvaluation(parameters)
    }

    @PostMapping("evaluateDCSCompliance")
    fun createDCSComplianceEvaluation(): EvaluationTaskResult {
        val dimension = "dcs_compliance"
        val dmploader = DMPLoaderParameters("", "zenodo/12.json")

        val parameters =  EvaluationTaskParameters(
            dmpLoaderParameters = dmploader,
            categories = null,
            dataLifecycle = DataLifecycle.PLANNING,
            dimensions = listOf(dimension)
        )

        return evaluationManagerService.createEvaluation(parameters)
    }
}