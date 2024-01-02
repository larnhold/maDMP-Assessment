package org.arnhold.evaluator.evaluation

import org.arnhold.evaluator.dataProvision.DataProviderService
import org.arnhold.evaluator.metricProcessing.MetricProcessingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationServiceImpl @Autowired constructor(
    val metricProcessingService: MetricProcessingService,
    val dataProviderService: DataProviderService
) : EvaluationService {
    override fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        val contextDMPId = dataProviderService.loadContextualizedDMP(parameters.dmpLoaderParameters)
        val contextDMP = dataProviderService.getContextualizedDMP(contextDMPId)
        return EvaluationTaskResult()
    }
}