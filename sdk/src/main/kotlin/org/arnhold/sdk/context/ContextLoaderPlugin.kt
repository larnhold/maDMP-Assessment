package org.arnhold.sdk.context
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.model.ContextResult
import org.arnhold.sdk.plugin.ConfigurablePlugin

interface ContextLoaderPlugin: ConfigurablePlugin<String, ContextProviderInformation> {
    fun getContext(dmpModel: Model): ContextResult
}