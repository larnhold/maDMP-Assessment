package org.arnhold.evaluator.plugin

import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.plugin.core.PluginRegistry
import org.springframework.stereotype.Component

@Component
class PluginLoaderImpl @Autowired constructor(
    val evaluationMethodRegistry: PluginRegistry<EvaluatorPlugin, String>,
    val dmpLoaderRegistry: PluginRegistry<DmpLoaderPlugin, String>,
    val contextLoaderRegistry: PluginRegistry<ContextLoaderPlugin, String>
): PluginLoader  {
    override fun getEvaluators(): List<EvaluatorPlugin> {
        return evaluationMethodRegistry.plugins
    }

    override fun getDMPLoaders(): List<DmpLoaderPlugin> {
        return dmpLoaderRegistry.plugins
    }

    override fun getDMPLoader(key: String):  DmpLoaderPlugin {
        try {
            return dmpLoaderRegistry.getRequiredPluginFor(key)
        } catch (e: Exception) {
            throw PluginNotFoundException(String.format("No DMP loader with identifier %S found", key))
        }
    }

    override fun getContextLoaders(): List<ContextLoaderPlugin> {
        return contextLoaderRegistry.plugins
    }

}