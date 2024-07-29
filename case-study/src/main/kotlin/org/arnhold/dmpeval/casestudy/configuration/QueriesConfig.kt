package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "queries")
data class QueriesConfig(
    var directory: String = ""
)