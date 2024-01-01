package org.arnhold.evaluator.shacl

import at.ac.tuwien.dcsojson.TransformationException
import org.arnhold.evaluator.dataProvision.DataProviderService
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
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
    var dataProviderService: DataProviderService,
    var shaclValidationService: ShaclValidationService,
    var resourcePatternResolver: ResourcePatternResolver
) {

    companion object {
        const val LOADER = "madmpfileloader"
    }

    @GetMapping("validateShapeMadmp/{shape}/{madmp}")
    @Throws(IOException::class, TransformationException::class)
    fun verifyShapeMadmp(@RequestParam(name = "shape", defaultValue = "shapes_02") shape: String, @RequestParam(name = "madmp", defaultValue = "zenodo/11") maDMP: String): ShaclValidationResult {
        val shapeFile = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape)).file
        val shapeModel = semanticService.loadModelFromFile(shapeFile)

        val maDMPFile = resourcePatternResolver.getResource(String.format("classpath:/maDMPs/%s.json", maDMP)).file
        val madmpModel = dataProviderService.loadDMP(LOADER, String.format("classpath:/maDMPs/%s.json", maDMP))
        val validation = shaclValidationService.validateShape(madmpModel, shapeModel)

        return shaclValidationService.createValidationResult(maDMPFile.name, shapeFile.name, validation)
    }

    @GetMapping("validateShape/{shape}")
    @Throws(IOException::class)
    fun verifyShape(@RequestParam(name = "shape", defaultValue = "shapes_02") shape: String): List<ShaclValidationResult> {
        val ressource = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape))
        val shapeFile = ressource.file
        val shapeModel = semanticService.loadModelFromFile(shapeFile)

        return Arrays.stream(resourcePatternResolver.getResources("classpath:/maDMPs/zenodo/*.json")).map {it ->
            val filename = it.filename
            val madmpModel = dataProviderService.loadDMP(LOADER, String.format("classpath:/maDMPs/%s.json", filename))
            try {
                val validation = shaclValidationService.validateShape(madmpModel, shapeModel)
                return@map filename?.let { itfilename -> shaclValidationService.createValidationResult(itfilename, shapeFile.name, validation) }
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }.sorted(Comparator.comparing(ShaclValidationResult::shape)).toList()
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

        val madmpModel = dataProviderService.loadDMP(LOADER, String.format("classpath:/maDMPs/%s.json", maDMP))

        val results = shapes.stream().map { x: File ->
            val shapeModel = semanticService.loadModelFromFile(x)
            val validation = shaclValidationService.validateShape(madmpModel, shapeModel)
            shaclValidationService.createValidationResult(maDMP, x.name, validation)
        }.toList()

        return results
    }
}
