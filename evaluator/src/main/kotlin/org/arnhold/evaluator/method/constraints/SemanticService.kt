package org.arnhold.evaluator.method.constraints

import org.apache.jena.rdf.model.Model
import java.io.File

interface SemanticService {
    fun loadModelFromFile(file: File): Model
}
