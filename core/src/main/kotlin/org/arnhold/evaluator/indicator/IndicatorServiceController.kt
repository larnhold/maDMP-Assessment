package org.arnhold.evaluator.indicator

import mu.KotlinLogging
import org.arnhold.evaluator.indicator.evaluationManager.EvaluationManagerService
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.model.EvaluationTaskResult
import org.arnhold.evaluator.indicator.evaluationManager.EvaluatorInformationDTO
import org.arnhold.evaluator.indicator.metricAggregation.MetricAggregationService
import org.arnhold.sdk.vocab.dqv.Measurement
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
class IndicatorServiceController @Autowired constructor(
    val evaluationManagerService: EvaluationManagerService,
    val metricAggregationService: MetricAggregationService
) {

    private val logger = KotlinLogging.logger {}

    @PostMapping("evaluate")
    fun createEvaluation(@RequestBody parameters: EvaluationTaskParameters): EvaluationTaskResult {
        val measurements = evaluationManagerService.createEvaluation(parameters)
        logger.info { "Return ${measurements.measurements.size} measurements" }
        return measurements
    }

    @GetMapping("{id}/measurements")
    fun getMeasurements(@PathVariable(name = "id") id: UUID): List<Measurement> {
        return listOf()
    }

    @PostMapping("{id}/aggregate")
    fun aggregate(@PathVariable(name = "id") id: UUID): List<Measurement> {
        return listOf()
    }

    @GetMapping("info/evaluators")
    fun getEvaluatorInformation(): List<EvaluatorInformationDTO> {
        return evaluationManagerService.getEvaluatorInformation().mapNotNull { EvaluatorInformationDTO(it.key.title, it.value.mapNotNull { it2 -> it2.title }) }
    }
}