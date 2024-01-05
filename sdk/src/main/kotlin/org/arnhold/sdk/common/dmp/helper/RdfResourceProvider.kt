package org.arnhold.sdk.common.dmp.helper

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource

abstract class RdfResourceProvider {

    companion object {
        const val PREFIX = "https://w3id.org/dcso/ns/core#"
    }

    fun toResource(model: Model,
                   name:String,
                   dataProperties: List<DataPropertyDefinition>,
                   objectProperties: List<ObjectPropertyDefinition>
    ): Resource {
        val resource = model.createResource(String.format("%s%s", PREFIX, name))
        dataProperties.forEach { addDataProperties(resource, it.predicate, it.values) }
        objectProperties.forEach { addObjectProperties(model, resource, it.predicate, it.objects, name, it.objName) }
        return resource
    }

    abstract fun toResource(model: Model, name: String): Resource

    private fun addDataProperties(subj: Resource, verb: Property, objects: List<String?>?) {
        objects?.let {
            objects.forEach { addDataProperty(subj, verb, it) }
        }
    }

    private fun addObjectProperties(model: Model, subj: Resource, verb: Property, objects: List<RdfResourceProvider?>?, rootObjName: String, name: String) {
        objects?.let {
            objects.forEachIndexed { index, obj -> addObjectProperty(model, subj, verb, obj, rootObjName + "_" + name + "_" + index) }
        }
    }

    private fun addDataProperty(subj: Resource, verb: Property, obj: String?) {
        obj?.let {
            subj.addProperty(verb, it)
        }
    }

    private fun addObjectProperty(model: Model, subj: Resource, verb: Property, obj: RdfResourceProvider?, name: String) {
        obj?.let {
            subj.addProperty(verb, obj.toResource(model, name))
        }
    }
}