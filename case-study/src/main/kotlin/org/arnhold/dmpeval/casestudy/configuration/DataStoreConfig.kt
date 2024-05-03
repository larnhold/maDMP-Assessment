package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "store")
data class DataStoreConfig(
    var directory: String = ""
)