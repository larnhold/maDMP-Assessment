package org.arnhold.evaluator.indicator.evaluationProvider

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : EvaluationProviderService {

    private val logger = KotlinLogging.logger {}

    override fun getAllEvaluators(): List<EvaluatorPlugin> {
        logger.info { "Get instance of all evaluators" }
        return pluginLoader.getEvaluators()
    }

    override suspend fun produceAllMeasurements(dmp: Model, context: List<DMPContext>, lifecycle: DataLifecycle): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle $lifecycle" }
        return coroutineScope {
            return@coroutineScope getAllEvaluators().map { async { it.getAllMeasurements(dmp, context, lifecycle) } }.awaitAll().flatten()
        }
    }
}