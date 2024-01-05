package org.arnhold.sdk.common.dmp.helper

import org.apache.jena.rdf.model.Property

class ObjectPropertyDefinition (
    val predicate: Property,
    val objects: List<RdfResourceProvider?>?,
    val objName: String
) {
    constructor(predicate: Property, obj: RdfResourceProvider?, objName: String): this(predicate, listOf(obj), objName)
}