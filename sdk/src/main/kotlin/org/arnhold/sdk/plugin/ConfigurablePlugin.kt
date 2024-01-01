package org.arnhold.sdk.plugin

import org.springframework.plugin.core.Plugin

interface ConfigurablePlugin<T>: Plugin<T> {

    fun getIdentifier(): String

    fun getRequiredConfigurationProperties(): List<String>

}