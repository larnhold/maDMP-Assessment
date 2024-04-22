package org.arnhold.sdk.vocab.rdf.parsing

import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

class ResourcePropertyDefinition (
    val predicate: Property,
    val objects: List<Resource?>?,
) {
    constructor(predicate: Property, obj: Resource?): this(predicate, listOf(obj))
}