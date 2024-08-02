package org.arnhold.dmpeval.casestudy.dmp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.configuration.DataStoreConfig
import org.arnhold.sdk.dmpLoader.DmpLoaderInformation
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Path

@Component
class MaDMPJsonJacksonLoaderImpl @Autowired constructor(
    val dataStoreConfig: DataStoreConfig
) : DmpLoaderPlugin {

    private val logger = KotlinLogging.logger {}
    val IDENTIFIER = "JSON-FILE"
    val dataDirectory: Path = Path.of(dataStoreConfig.directory, "/case-study/maDMPs")

    override fun getPluginIdentifier(): String {
        return IDENTIFIER
    }

    override fun getPluginInformation(): DmpLoaderInformation {
        TODO("Not yet implemented")
    }

    private fun getFile(location: String): File {
        return dataDirectory.resolve(location).toFile()
    }

    override fun loadDMP(identifier: String, dcsOntology: OntModel): Model {
        logger.info { "Load DMP using identifier $identifier" }
        try {
            val file = getFile(identifier)
            logger.info { "Load from ${file.path}" }
            val dmp = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(file, DMPWrapper::class.java)

            logger.info { "Convert to RDF" }
            val model = dcsOntology.baseModel
            dmp.dmp.toResource(model, "dmp_0")

            return model
        } catch (e: Exception) {
            logger.error { e }
            throw RuntimeException(e)
        }
    }

    override fun supports(p0: String): Boolean {
        return p0 == IDENTIFIER
    }
}
