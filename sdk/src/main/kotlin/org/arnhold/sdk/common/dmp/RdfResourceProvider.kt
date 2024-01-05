package org.arnhold.sdk.common.dmp

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource

interface RdfResourceProvider {
    fun toResource(model: Model, name: String): Resource
}