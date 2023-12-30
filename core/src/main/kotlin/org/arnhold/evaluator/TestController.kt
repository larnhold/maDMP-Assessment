package org.arnhold.evaluator

import org.arnhold.evaluator.plugin.PluginLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/test123")
class TestController @Autowired constructor(
        val pluginLoader: PluginLoader
) {
    @GetMapping("test")
    fun test(): String {
        val evaluators = pluginLoader.getEvaluators()
        val dmpLoader = pluginLoader.getDMPLoader("default")
        val contextProviders = pluginLoader.getContextLoaders()

        return ""
    }
}