package org.arnhold.evaluator.evaluation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/evaluation")
class EvaluationController @Autowired constructor(val evaluationService: EvaluationService) {
    @PostMapping("create")
    fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        return EvaluationTaskResult()
    }

    @GetMapping("{id}/metrics")
    fun getMetrics(@PathVariable(name = "id") id: String): String {
        return ""
    }
}