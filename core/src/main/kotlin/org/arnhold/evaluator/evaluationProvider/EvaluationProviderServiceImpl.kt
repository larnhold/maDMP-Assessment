package org.arnhold.evaluator.evaluationProvider

import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : EvaluationProviderService {
    override fun getAllEvaluators(): List<DimensionEvaluatorPlugin> {
        return pluginLoader.getEvaluators()
    }
}