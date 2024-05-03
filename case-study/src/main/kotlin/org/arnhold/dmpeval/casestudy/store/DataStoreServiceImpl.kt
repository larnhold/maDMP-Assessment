package org.arnhold.dmpeval.casestudy.store

import com.fasterxml.jackson.databind.ObjectWriter
import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.dmpeval.casestudy.configuration.DataStoreConfig
import org.arnhold.sdk.store.DataStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Path
import java.util.*


@Component
class DataStoreServiceImpl @Autowired constructor(
    val dataStoreConfig: DataStoreConfig,
    val objectWriter: ObjectWriter
) : DataStoreService {

    private val logger = KotlinLogging.logger {}

    override fun saveModel(id: UUID, model: Model) {
        try {
            val savePath = Path.of(dataStoreConfig.directory, String.format("%s.ttl", id.toString()))
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
        val getFromPath = Path.of(dataStoreConfig.directory, String.format("%s.ttl", id.toString()))
        logger.info { "Get model with id $id from path $getFromPath" }
        val model: Model = ModelFactory.createDefaultModel()
        return model.read(getFromPath.toFile().absolutePath)
    }

    override fun <T> saveAsJson(id: UUID, data: Any) {
        try {
            val jsonData = objectWriter.writeValueAsString(data as T)
            val savePath = Path.of(dataStoreConfig.directory, String.format("%s.json", id.toString()))
            logger.info { "Save object with id $id to $savePath as JSON"}
            FileWriter(savePath.toFile()).use { fileWriter ->
                fileWriter.write(jsonData)
            }
        } catch (e: Exception) {
            logger.info { "Error saving object with id $id as JSON: Error $e" }
            throw e
        }
    }
}