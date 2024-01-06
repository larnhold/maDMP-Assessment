package org.arnhold.evaluator.dataProvision

import org.arnhold.evaluator.plugin.PluginLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/info")
class DataProviderInfoController @Autowired constructor(
    val pluginLoader: PluginLoader
) {

    @GetMapping("dmp-providers")
    fun getRegisteredDmpProviders(): List<String> {
        return pluginLoader.getDMPLoaders().map { it.getPluginIdentifier() }
    }

    @GetMapping("context-providers")
    fun getRegisteredContextProviders(): List<String> {
        return pluginLoader.getContextLoaders().map { it.getPluginIdentifier() }
    }

}