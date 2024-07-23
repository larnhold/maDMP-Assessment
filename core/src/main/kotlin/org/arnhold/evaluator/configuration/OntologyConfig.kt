package org.arnhold.evaluator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ontology")
data class OntologyConfig(
    var DCSLocation: String = "./data/ontologies/dcso-4.0.1.ttl",
    var DMPDQVLocation: String = "./data/ontologies/dmpqv.ttl"
)