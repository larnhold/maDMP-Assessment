package org.arnhold.dmpeval.casestudy.store

import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.dmpeval.casestudy.configuration.TripleStoreConfig
import org.arnhold.sdk.store.DataStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Path
import java.util.*


@Component
class DataStoreServiceImpl @Autowired constructor(
    val tripleStoreConfig: TripleStoreConfig
) : DataStoreService {

    private val logger = KotlinLogging.logger {}

    override fun saveModel(id: UUID, model: Model) {
        try {
            val savePath = Path.of(tripleStoreConfig.directory, String.format("%s.ttl", id.toString()))
            logger.info { "Save model with id $id to $savePath" }
            FileOutputStream(savePath.toFile(), false).use { fileWriter ->
                RDFDataMgr.write(fileWriter, model, Lang.TURTLE)
                fileWriter.flush()
            }
        } catch (e: IOException) {
            logger.info { "Error saving model with id $id: Error $e" }
            throw e
        }
    }

    override fun getModel(id: UUID): Model {
        val getFromPath = Path.of(tripleStoreConfig.directory, String.format("%s.ttl", id.toString()))
        logger.info { "Get model with id $id from path $getFromPath" }
        val model: Model = ModelFactory.createDefaultModel()
        return model.read(getFromPath.toFile().absolutePath)
    }

    override fun updateModel(id: UUID, model: Model) {
        logger.info { "Update model with id $id" }
        saveModel(id, model)
    }

    override fun getAllModels(): List<UUID> {
        return listOf()
    }

    override fun removeModel(id: UUID) {
        // TODO
    }
}