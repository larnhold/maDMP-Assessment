package org.arnhold.evaluator.plugin

import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin

interface PluginLoader {
    fun getEvaluators(): List<DimensionEvaluatorPlugin>
    fun getDMPLoaders(): List<DmpLoaderPlugin>
    fun getDMPLoader(key: String) : DmpLoaderPlugin
    fun getContextLoaders(): List<ContextLoaderPlugin>
}