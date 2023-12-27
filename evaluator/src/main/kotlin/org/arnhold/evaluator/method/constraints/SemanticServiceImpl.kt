package org.arnhold.evaluator.method.constraints

import org.apache.jena.rdf.model.Model
import org.springframework.stereotype.Component
import org.apache.jena.riot.RDFDataMgr
import java.io.File

@Component
class SemanticServiceImpl : SemanticService {
    override fun loadModelFromFile(file: File): Model {
        return RDFDataMgr.loadModel(file.absolutePath)
    }
}
