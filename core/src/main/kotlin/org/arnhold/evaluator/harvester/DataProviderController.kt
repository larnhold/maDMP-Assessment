package org.arnhold.evaluator.harvester

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.harvester.dataProvider.DataProviderService
import org.arnhold.evaluator.indicator.evaluationManager.DMPLoaderParameters
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController("/api/data-provider")
class DataProviderController @Autowired constructor(
    val dataProviderService: DataProviderService,
    val pluginLoader: PluginLoader
) {

    @GetMapping("extensions")
    fun getExtensions(): List<String> {
        return dataProviderService.getExtensions().map { it.key }
    }

    @PostMapping("loadContext")
    fun loadDMPContext(model: Model): List<DMPContext> {
        return runBlocking(Dispatchers.Default) {
            return@runBlocking dataProviderService.loadContext(model)
        }
    }

    @PostMapping("loadDMP")
    fun loadDMP(parameters: DMPLoaderParameters): UUID {
        return dataProviderService.loadDMP(parameters)
    }

    @GetMapping("info/dmp-providers")
    fun getRegisteredDmpProviders(): List<String> {
        return pluginLoader.getDMPLoaders().map { it.getPluginIdentifier() }
    }

    @GetMapping("info/context-providers")
    fun getRegisteredContextProviders(): List<String> {
        return pluginLoader.getContextLoaders().map { it.getPluginIdentifier() }
    }

}