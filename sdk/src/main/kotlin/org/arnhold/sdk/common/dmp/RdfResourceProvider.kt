package org.arnhold.sdk.common.dmp

import org.apache.jena.rdf.model.Resource

interface RdfResourceProvider {
    fun toResource(resource: Resource): Resource
}