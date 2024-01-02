package org.arnhold.dmpeval.casestudy.dmp

import at.ac.tuwien.dcsojson.DcsoJsonTransformer
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Path

@Component
class MaDMPFileLoaderImpl @Autowired constructor(
        var dcsoJsonTransformer: DcsoJsonTransformer,
) : DmpLoaderPlugin {

    companion object {
        const val IDENTIFIER = "madmpfileloader"
        val dataDirectory = Path.of("/home/lukasa/Documents/thesis/maDMP-Assesment-lukas/data/case-study/maDMPs")
    }

    private fun getFile(location: String): File {
        return dataDirectory.resolve(location).toFile()
    }

    override fun loadDMP(identifier: String, dcsOntology: OntModel): Model {
        try {
            return dcsoJsonTransformer.convertPlainToModel(getFile(identifier), dcsOntology)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getIdentifier(): String {
        return IDENTIFIER
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return p0 == IDENTIFIER
    }
}
