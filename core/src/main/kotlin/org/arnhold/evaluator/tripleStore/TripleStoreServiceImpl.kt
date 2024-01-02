package org.arnhold.evaluator.tripleStore

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.evaluator.configuration.TripleStoreConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.file.Path
import java.util.*


@Component
class TripleStoreServiceImpl @Autowired constructor(
    val tripleStoreConfig: TripleStoreConfig
) : TripleStoreService {

    override fun saveModel(id: UUID, model: Model) {
        try {
            FileOutputStream(Path.of(tripleStoreConfig.directory, String.format("%s.ttl", id.toString())).toFile(), false).use { fileWriter ->
                RDFDataMgr.write(fileWriter, model, Lang.TURTLE)
            }
        } catch (e: IOException) {
            throw e
        }
    }

    override fun getModel(id: UUID): Model {
        val model: Model = ModelFactory.createDefaultModel()
        val inputStream: InputStream = FileInputStream(Path.of(tripleStoreConfig.directory, String.format("%s.ttl", id.toString())).toFile())
        return model.read(Path.of(tripleStoreConfig.directory, String.format("%s.ttl", id.toString())).toFile().absolutePath)
    }

    override fun updateModel(id: UUID, model: Model) {
        saveModel(id, model)
    }

    override fun getAllModels(): List<UUID> {
        return listOf()
    }

    override fun removeModel(id: UUID) {
        // TODO
    }
}