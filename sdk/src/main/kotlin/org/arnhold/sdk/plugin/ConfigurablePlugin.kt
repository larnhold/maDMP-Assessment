package org.arnhold.sdk.plugin

import org.springframework.plugin.core.Plugin

interface ConfigurablePlugin<T, Q>: Plugin<T> {

    fun getPluginIdentifier(): String

    fun getPluginInformation(): Q

}