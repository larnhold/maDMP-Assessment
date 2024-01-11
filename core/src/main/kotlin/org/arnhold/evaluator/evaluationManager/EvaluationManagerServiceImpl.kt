package org.arnhold.evaluator.evaluationManager

import org.arnhold.evaluator.dataProvision.DataProviderService
import org.arnhold.evaluator.evaluationProvider.EvaluationProviderService
import org.arnhold.evaluator.metricProcessing.MetricProcessingService
import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationManagerServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
    val dataProviderService: DataProviderService,
    val metricProcessingService: MetricProcessingService
) : EvaluationManagerService {
    override fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        val contextDMPId = dataProviderService.loadContextualizedDMP(parameters.dmpLoaderParameters)
        val contextDMP = dataProviderService.getContextualizedDMP(contextDMPId)
        val measurements = metricProcessingService.produceAllMeasurements(contextDMP, parameters.dataLifecycle)
        return EvaluationTaskResult(measurements = measurements)
    }

    override fun getEvaluatorInformation(): Map<Category, List<Dimension>> {
        return evaluationProviderService.getAllEvaluators().map { it.getPluginInformation() }.groupBy ( {it.belongsToCategory}, {it.applicableDimension} )
    }
}