package org.arnhold.evaluator.harvester

import org.arnhold.evaluator.harvester.dataProvider.DataProviderService
import org.arnhold.evaluator.plugin.PluginLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/data-provider")
class DataProviderController @Autowired constructor(
    val dataProviderService: DataProviderService,
    val pluginLoader: PluginLoader
) {

    @PostMapping("loadDMP")
    fun loadDMP(): String {
        val test = dataProviderService.getDCSOntology()
        return ""
    }

    @PostMapping("loadContext")
    fun loadDMPContext(): Boolean {
        return true
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