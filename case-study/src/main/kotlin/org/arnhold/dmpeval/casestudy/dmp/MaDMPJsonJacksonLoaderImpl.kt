package org.arnhold.dmpeval.casestudy.dmp

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.dmpLoader.DmpLoaderInformation
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Path

@Component
class MaDMPJsonJacksonLoaderImpl: DmpLoaderPlugin {

    companion object {
        const val IDENTIFIER = ""
        val dataDirectory: Path = Path.of("./data/case-study/maDMPs")
    }

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
        try {
            val file = getFile(identifier)
            val dmp = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(file, DMPWrapper::class.java)

            val model = dcsOntology.baseModel
            dmp.dmp.toResource(model, "dmp_0")

            return model
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun supports(p0: String): Boolean {
        return p0 == IDENTIFIER
    }
}
