package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "triplestore")
data class TripleStoreConfig(
    var directory: String = ""
)