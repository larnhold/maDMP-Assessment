package org.arnhold.sdk.context
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.plugin.ConfigurablePlugin

interface ContextLoaderPlugin: ConfigurablePlugin<String, ContextProviderInformation> {
    fun addContext(dmpModel: Model)
}