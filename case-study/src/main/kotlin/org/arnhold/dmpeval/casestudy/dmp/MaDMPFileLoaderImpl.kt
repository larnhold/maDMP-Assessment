package org.arnhold.dmpeval.casestudy.dmp

import at.ac.tuwien.dcsojson.DcsoJsonTransformer
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

@Component
class MaDMPFileLoaderImpl @Autowired constructor(var dcsoJsonTransformer: DcsoJsonTransformer) : DmpLoaderPlugin {

    override fun fromIdentifier(identifier: File): Model {
        try {
            return dcsoJsonTransformer.convertPlainToModel(identifier)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}
