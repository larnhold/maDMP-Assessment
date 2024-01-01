package org.arnhold.dmpeval.casestudy.dmp

import at.ac.tuwien.dcsojson.DcsoJsonTransformer
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Component
import java.io.File

@Component
class MaDMPFileLoaderImpl @Autowired constructor(
        var dcsoJsonTransformer: DcsoJsonTransformer,
        var resourcePatternResolver: ResourcePatternResolver
) : DmpLoaderPlugin {

    companion object {
        const val IDENTIFIER = "madmpfileloader"
    }

    private fun loadFileFromClassPath(location: String): File {
        val file = resourcePatternResolver.getResource(location).file
        return file
    }

    override fun loadDMP(identifier: String): Model {
        try {
            return dcsoJsonTransformer.convertPlainToModel(loadFileFromClassPath(identifier))
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
