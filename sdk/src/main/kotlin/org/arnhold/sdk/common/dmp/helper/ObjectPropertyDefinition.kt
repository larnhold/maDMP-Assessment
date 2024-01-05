package org.arnhold.sdk.common.dmp.helper

import org.apache.jena.rdf.model.Property

class ObjectPropertyDefinition (
    val predicate: Property,
    val objects: List<RdfResourceProvider?>?,
    val rootObjName: String,
    val objName: String
) {
    constructor(predicate: Property, obj: RdfResourceProvider?, rootObjName: String, objName: String): this(predicate, listOf(obj), rootObjName, objName)
}