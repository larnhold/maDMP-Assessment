package org.arnhold.evaluation.method.constraints.shacl

import org.apache.jena.rdf.model.Model
import org.apache.jena.shacl.ValidationReport

interface ShaclValidationService {
    fun validateShape(maDMPGraph: Model, shapesGraph: Model): ValidationReport
    fun createValidationResult(maDMP: String, shape: String, report: ValidationReport): ShaclValidationResult
}
