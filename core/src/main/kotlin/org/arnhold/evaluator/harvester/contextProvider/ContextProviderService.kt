package org.arnhold.evaluator.harvester.contextProvider

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.ContextProviderInformation
import org.arnhold.sdk.vocab.context.DMPContext

interface ContextProviderService {
    fun getAvailableContext(model: Model): List<DMPContext>
    fun getContextFromLoader(identifier: String, model: Model): List<DMPContext>
    fun getContextProviderIdentifiers(): List<String>
    fun getContextProviderInformation(identifier: String): ContextProviderInformation
    fun getAllContextProviderInformation(identifier: String): Map<String, ContextProviderInformation>
}