package org.arnhold.evaluator.evaluationManager

import org.arnhold.sdk.common.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/evaluation")
class EvaluationController @Autowired constructor(
    val evaluationManagerService: EvaluationManagerService
) {
    @PostMapping("create")
    fun createEvaluation(@RequestBody parameters: EvaluationTaskParameters): EvaluationTaskResult {
        return evaluationManagerService.createEvaluation(parameters)
    }

    @GetMapping("{id}/measurements")
    fun getMeasurements(@PathVariable(name = "id") id: UUID): List<Measurement> {
        return listOf()
    }

    @GetMapping("/evaluators")
    fun getEvaluatorInformation(): List<EvaluatorInformationDTO> {
        return evaluationManagerService.getEvaluatorInformation().map { EvaluatorInformationDTO(it.key.title, it.value.map { it2 -> it2.title }) }
    }
}