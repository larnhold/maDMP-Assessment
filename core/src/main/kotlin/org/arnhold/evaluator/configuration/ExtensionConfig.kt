package org.arnhold.evaluator.configuration

import org.arnhold.sdk.vocab.constants.Extension
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "extension")
class ExtensionConfig {
    var ontologies: MutableList<OntologyInfo> = mutableListOf()
}

class OntologyInfo {
    lateinit var name: Extension
    lateinit var location: String
}