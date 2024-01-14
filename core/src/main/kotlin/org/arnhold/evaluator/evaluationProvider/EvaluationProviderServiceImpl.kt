package org.arnhold.evaluator.evaluationProvider

import mu.KotlinLogging
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : EvaluationProviderService {

    private val logger = KotlinLogging.logger {}
    override fun getAllEvaluators(): List<DimensionEvaluatorPlugin> {
        logger.info { "Get instance of all evaluators" }
        return pluginLoader.getEvaluators()
    }
}