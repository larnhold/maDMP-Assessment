package org.arnhold.evaluation.constraints

import org.apache.jena.rdf.model.Model
import java.io.File

interface SemanticService {
    fun loadModelFromFile(file: File): Model
}
