package org.arnhold.evaluator.plugin

import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.contextLoader.ContextLoaderPlugin
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.plugin.core.PluginRegistry
import org.springframework.stereotype.Component

@Component
class PluginLoaderImpl @Autowired constructor(
        val evaluationMethodRegistry: PluginRegistry<EvaluationMethodPlugin, Metric>,
        val dmpLoaderRegistry: PluginRegistry<DmpLoaderPlugin, String>,
        val contextLoaderRegistry: PluginRegistry<ContextLoaderPlugin, String>
): PluginLoader  {
    override fun getEvaluators(): List<EvaluationMethodPlugin> {
        return evaluationMethodRegistry.plugins
    }

    override fun getDMPLoaders(): List<DmpLoaderPlugin> {
        return dmpLoaderRegistry.plugins;
    }

    override fun getDMPLoader(key: String):  DmpLoaderPlugin {
        return dmpLoaderRegistry.getRequiredPluginFor(key)
    }

    override fun getContextLoaders(): List<ContextLoaderPlugin> {
        return contextLoaderRegistry.plugins
    }

}