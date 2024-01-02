package org.arnhold.evaluator.shacl

import org.apache.jena.rdf.model.Model
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.apache.jena.shacl.ShaclValidator
import org.apache.jena.shacl.Shapes
import org.apache.jena.shacl.ValidationReport
import org.apache.jena.shacl.validation.ReportEntry
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ShaclValidationServiceImpl : ShaclValidationService {
    override fun validateShape(maDMPGraph: Model, shapesGraph: Model): ValidationReport {
        val shapes = Shapes.parse(shapesGraph)
        val graph = maDMPGraph.graph

        val result = ShaclValidator.get().validate(shapes, graph)
        RDFDataMgr.write(System.out, result.model, Lang.TTL)
        return result
    }

    override fun createValidationResult(maDMP: String, shape: String, report: ValidationReport?): ShaclValidationResult {
        val conforms: Boolean = report?.conforms() ?: false
        val messages: List<String> = report!!.entries.stream().map { obj: ReportEntry -> obj.message() }.collect(Collectors.toList())

        return ShaclValidationResult(maDMP, shape, conforms, messages)
    }
}
