package org.arnhold.evaluator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ontology")
data class OntologyConfig(
    var DCSLocation: String = "",
    var DMPDQVLocation: String = ""
)