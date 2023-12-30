package org.arnhold.dmpeval.casestudy.evaluation.shacl

import org.apache.jena.rdf.model.Model
import java.io.File

interface SemanticService {
    fun loadModelFromFile(file: File): Model
}
