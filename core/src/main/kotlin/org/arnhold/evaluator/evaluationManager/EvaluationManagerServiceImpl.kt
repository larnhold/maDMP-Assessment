package org.arnhold.evaluator.evaluationManager

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.reasoner.Reasoner
import org.apache.jena.reasoner.ReasonerRegistry
import org.arnhold.evaluator.dataProvision.DataProviderService
import org.arnhold.evaluator.evaluationProvider.EvaluationProviderService
import org.arnhold.evaluator.metricProcessing.MetricProcessingService
import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EvaluationManagerServiceImpl @Autowired constructor(
    val evaluationProviderService: EvaluationProviderService,
    val dataProviderService: DataProviderService,
    val metricProcessingService: MetricProcessingService
) : EvaluationManagerService {
    override fun createEvaluation(parameters: EvaluationTaskParameters): EvaluationTaskResult {
        val contextDMPId = dataProviderService.loadContextualizedDMP(parameters.dmpLoaderParameters)
        val contextDMP = dataProviderService.getContextualizedDMP(contextDMPId)
        contextDMP.setNsPrefix("dmpdqv", DMPDQV.URI_PREFIX)
        contextDMP.setNsPrefix("dqv", DMPDQV.DQV_PREFIX)
        val measurements = metricProcessingService.produceAllMeasurements(contextDMP, parameters.dataLifecycle)

        //After adding measurements to contextDMP save and run reasoner over dmpdqv to get correct rdf types from predicate relations
        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(dataProviderService.getDMPDQVOntology())
        val reasonedModel = ModelFactory.createInfModel(reasoner, contextDMP)
        dataProviderService.saveModel(reasonedModel)

        return EvaluationTaskResult(
            success = true,
            message = "no messages yet",
            evaluationId = UUID.randomUUID().toString(),
            measurements = measurements
        )
    }

    override fun getEvaluatorInformation(): Map<Category, List<Dimension>> {
        return evaluationProviderService.getAllEvaluators().map { it.getPluginInformation() }.groupBy ( {it.belongsToCategory}, {it.applicableDimension} )
    }
}