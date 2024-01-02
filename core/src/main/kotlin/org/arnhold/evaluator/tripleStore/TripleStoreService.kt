package org.arnhold.evaluator.tripleStore

import org.apache.jena.rdf.model.Model
import org.objectweb.asm.commons.StaticInitMerger
import java.util.UUID

interface TripleStoreService {

    fun  saveModel(id: UUID, model: Model)

    fun getModel(id: UUID): Model

    fun updateModel(id: UUID, model: Model)

    fun getAllModels(): List<UUID>

    fun removeModel(id: UUID)

}