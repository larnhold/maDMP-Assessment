package org.arnhold.sdk.common.rdf.parsing

import org.apache.jena.rdf.model.Property

data class DataPropertyDefinition(
    val predicate: Property,
    val values: List<String?>?
) {
    constructor(predicate: Property, value: String?): this(predicate, listOf(value))
}
