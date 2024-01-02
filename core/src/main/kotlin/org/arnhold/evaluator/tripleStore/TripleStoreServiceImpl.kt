package org.arnhold.evaluator.tripleStore

import org.apache.jena.query.Dataset
import org.apache.jena.rdf.model.Model
import org.apache.jena.tdb.TDBFactory
import org.arnhold.evaluator.configuration.TripleStoreConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class TripleStoreServiceImpl @Autowired constructor(
    val tripleStoreConfig: TripleStoreConfig
) : TripleStoreService {

    final var store: Dataset

    init {
        val directory = String.format("%s", tripleStoreConfig.directory)
        store = TDBFactory.createDataset(directory)
    }

    override fun saveModel(id: UUID, model: Model) {
        store.addNamedModel(id.toString(), model)
    }

    override fun getModel(id: UUID): Model {
        return store.getNamedModel(id.toString())
    }

    override fun updateModel(id: UUID, model: Model) {
        store.replaceNamedModel(id.toString(), model)
    }

    override fun getAllModels(): List<UUID> {
        return store.listNames().asSequence().map { UUID.fromString(it) }.toList()
    }

    override fun removeModel(id: UUID) {
        store.removeNamedModel(id.toString())
    }
}