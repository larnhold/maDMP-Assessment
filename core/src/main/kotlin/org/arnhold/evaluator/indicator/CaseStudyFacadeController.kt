package org.arnhold.evaluator.indicator

import org.arnhold.evaluator.indicator.evaluationManager.EvaluationManagerService
import org.arnhold.sdk.model.*
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
        val dmploader = DMPLoaderParameters("", "dcs-repo-examples/ex8-dmp-minimal-content.json")

        val parameters =  EvaluationTaskParameters(
            dmpLoaderParameters = dmploader,
            categories = listOf(),
            dataLifecycle = DataLifecycle.PLANNING,
            dimensions = listOf()
        )

        return evaluationManagerService.createEvaluation(parameters)
    }

    @PostMapping("evaluateDCSCompliance")
    fun createDCSComplianceEvaluation(): EvaluationTaskResult {
        val dmploader = DMPLoaderParameters("", "zenodo/12.json")

        val parameters =  EvaluationTaskParameters(
            dmpLoaderParameters = dmploader,
            categories = listOf(),
            dataLifecycle = DataLifecycle.PLANNING,
            dimensions = listOf(
                EvaluationDimensionConstants.DCS_COMPLIANCE.toString(),
                EvaluationDimensionConstants.DCS_COMPLETENESS.toString()
            )
        )

        return evaluationManagerService.createEvaluation(parameters)
    }
}