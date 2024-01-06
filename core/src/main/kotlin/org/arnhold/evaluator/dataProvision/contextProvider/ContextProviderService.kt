package org.arnhold.evaluator.dataProvision.contextProvider

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.ContextProviderInformation

interface ContextProviderService {
    fun addContextFromContextProvider(contextProviderId: String, model: Model)
    fun addAllContextToModel(model: Model)
    fun getContextProviderIdentifiers(): List<String>
    fun getContextProviderInformation(identifier: String): ContextProviderInformation
    fun getAllContextProviderInformation(identifier: String): Map<String, ContextProviderInformation>
}