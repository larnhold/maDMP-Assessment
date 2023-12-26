package org.arnhold.dmpeval.casestudy.loader

import at.ac.tuwien.dcsojson.DcsoJsonTransformer
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.dmpLoader.MaDMPLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.File

@Component
@Service
class MaDMPFileLoaderImpl @Autowired constructor(var dcsoJsonTransformer: DcsoJsonTransformer) : MaDMPLoader {

    override fun fromIdentifier(identifier: File): Model {
        try {
            return dcsoJsonTransformer.convertPlainToModel(identifier)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
