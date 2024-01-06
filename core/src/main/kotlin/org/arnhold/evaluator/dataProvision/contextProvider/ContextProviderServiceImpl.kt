package org.arnhold.evaluator.dataProvision.contextProvider

import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.context.ContextProviderInformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ContextProviderServiceImpl @Autowired constructor(
    pluginLoader: PluginLoader
): ContextProviderService {
    override fun addContextFromContextProvider(contextProviderId: String, model: Model) {
        TODO("Not yet implemented")
    }

    override fun addAllContextToModel(model: Model) {
        TODO("Not yet implemented")
    }

    override fun getContextProviderIdentifiers(): List<String> {
        TODO("Not yet implemented")
    }

    override fun getContextProviderInformation(identifier: String): ContextProviderInformation {
        TODO("Not yet implemented")
    }

    override fun getAllContextProviderInformation(identifier: String): Map<String, ContextProviderInformation> {
        TODO("Not yet implemented")
    }
}