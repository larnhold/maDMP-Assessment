package org.arnhold.sdk.tools.rdfParsing

import org.apache.jena.rdf.model.Property

data class DataPropertyDefinition(
    val predicate: Property,
    val values: List<String?>?
) {
    constructor(predicate: Property, value: String?): this(predicate, listOf(value))
}
