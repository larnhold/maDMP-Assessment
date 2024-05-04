package org.arnhold.evaluator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "extension")
class ExtensionConfig {
    var ontologies: MutableList<OntologyInfo> = mutableListOf()
}

class OntologyInfo {
    lateinit var name: String
    lateinit var location: String
}