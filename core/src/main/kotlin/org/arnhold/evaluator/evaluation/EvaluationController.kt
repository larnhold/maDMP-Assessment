package org.arnhold.evaluator.evaluation

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
    val evaluationService: EvaluationService
) {
    @PostMapping("create")
    fun createEvaluation(@RequestBody parameters: EvaluationTaskParameters): EvaluationTaskResult {
        return evaluationService.createEvaluation(parameters)
    }

    @GetMapping("{id}/measurements")
    fun getMeasurements(@PathVariable(name = "id") id: UUID): List<Measurement<String>> {
        return listOf()
    }
}