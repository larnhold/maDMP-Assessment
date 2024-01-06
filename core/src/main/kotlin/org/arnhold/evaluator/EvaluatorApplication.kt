package org.arnhold.evaluator

import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.plugin.core.config.EnablePluginRegistries

@SpringBootApplication
@ComponentScan(basePackages = ["org.arnhold"])
@EnablePluginRegistries(DimensionEvaluatorPlugin::class, ContextLoaderPlugin::class, DmpLoaderPlugin::class)
class EvaluatorApplication

fun main(args: Array<String>) {
    runApplication<EvaluatorApplication>(*args)
}
