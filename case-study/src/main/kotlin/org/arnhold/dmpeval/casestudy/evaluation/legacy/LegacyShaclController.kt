package org.arnhold.dmpeval.casestudy.evaluation.legacy

import at.ac.tuwien.dcsojson.TransformationException
import org.arnhold.evaluation.method.constraints.SemanticService
import org.arnhold.evaluation.method.constraints.shacl.ShaclValidationService
import org.arnhold.evaluation.method.constraints.shacl.ShaclValidationResult
import org.arnhold.sdk.dmpLoader.MaDMPLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.IOException
import java.util.*

@RestController
@RequestMapping("/api/shacl/legacy")
class LegacyShaclController @Autowired constructor(
        var semanticService: SemanticService,
        var maDMPLoader: MaDMPLoader,
        var shaclValidationService: ShaclValidationService,
        var resourcePatternResolver: ResourcePatternResolver
) {
    @GetMapping("validateShapeMadmp/{shape}/{madmp}")
    @Throws(IOException::class, TransformationException::class)
    fun verifyShapeMadmp(@RequestParam(name = "shape", defaultValue = "shapes_02") shape: String, @RequestParam(name = "madmp", defaultValue = "zenodo/11") maDMP: String): ShaclValidationResult {
        val shapeFile = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape)).file
        val shapeModel = semanticService.loadModelFromFile(shapeFile)

        val maDMPFile = resourcePatternResolver.getResource(String.format("classpath:/maDMPs/%s.json", maDMP)).file
        val madmpModel = maDMPLoader.fromIdentifier(maDMPFile)
        val validation = shaclValidationService.validateShape(madmpModel, shapeModel)

        return shaclValidationService.createValidationResult(maDMPFile.name, shapeFile.name, validation)
    }

    @GetMapping("validateShape/{shape}")
    @Throws(IOException::class)
    fun verifyShape(@RequestParam(name = "shape", defaultValue = "shapes_02") shape: String): List<ShaclValidationResult> {
        val ressource = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape))
        val shapeFile = ressource.file
        val shapeModel = semanticService.loadModelFromFile(shapeFile)

        val res = Arrays.stream(resourcePatternResolver.getResources("classpath:/maDMPs/zenodo/*.json")).map { element: Resource ->
            try {
                val file = element.file
                val madmpModel = maDMPLoader.fromIdentifier(file)
                val validation = shaclValidationService.validateShape(madmpModel, shapeModel)
                return@map shaclValidationService.createValidationResult(file.name, shapeFile.name, validation)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }.sorted(Comparator.comparing(ShaclValidationResult::shape)).toList()

        return res
    }

    @GetMapping("validateMadmp/{madmp}")
    @Throws(IOException::class, TransformationException::class)
    fun verifyMaDMP(@RequestParam(name = "madmp", defaultValue = "zenodo/11") maDMP: String): List<ShaclValidationResult> {
        val shapes = Arrays.stream(resourcePatternResolver.getResources("classpath:/original-evaluation/shacl_constraints/*.ttl")).map { element: Resource ->
            try {
                return@map element.file
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }.toList()

        val maDMPFile = resourcePatternResolver.getResource(String.format("classpath:/maDMPs/%s.json", maDMP)).file
        val maDmpModel = maDMPLoader.fromIdentifier(maDMPFile)

        val results = shapes.stream().map { x: File ->
            val shapeModel = semanticService.loadModelFromFile(x)
            val validation = shaclValidationService.validateShape(maDmpModel, shapeModel)
            shaclValidationService.createValidationResult(maDMPFile.name, x.name, validation)
        }.toList()

        return results
    }
}