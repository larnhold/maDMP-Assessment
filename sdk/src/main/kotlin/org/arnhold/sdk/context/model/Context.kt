package org.arnhold.sdk.context.model

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

data class Context(
    val key: String,
    val value: String,
    val source: String
): RdfResourceProvider (){
    override fun toResource(model: Model, name: String): Resource {
        TODO("Not yet implemented")
    }
}
