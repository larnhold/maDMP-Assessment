package org.arnhold.sdk.store

import org.apache.jena.rdf.model.Model
import java.util.UUID

interface DataStoreService {

    fun  saveModel(id: UUID, model: Model)

    fun getModel(id: UUID): Model

    fun updateModel(id: UUID, model: Model)

    fun getAllModels(): List<UUID>

    fun removeModel(id: UUID)

}