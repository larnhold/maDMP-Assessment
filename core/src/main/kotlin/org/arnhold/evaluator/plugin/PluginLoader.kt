package org.arnhold.evaluator.plugin

import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin

interface PluginLoader {
    fun getEvaluators(): List<EvaluationMethodPlugin>
    fun getDMPLoaders(): List<DmpLoaderPlugin>
    fun getDMPLoader(key: String) : DmpLoaderPlugin
    fun getContextLoaders(): List<ContextLoaderPlugin>
}