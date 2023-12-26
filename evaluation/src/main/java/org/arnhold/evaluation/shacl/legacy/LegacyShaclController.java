package org.arnhold.evaluation.shacl.legacy;

import org.arnhold.evaluation.shacl.ShaclValidationResult;
import org.arnhold.plugins.MaDMPLoader;
import org.arnhold.evaluation.constraints.SemanticService;
import org.arnhold.evaluation.constraints.shacl.ShaclValidationService;
import org.example.dcsojson.TransformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/shacl/legacy")
public class LegacyShaclController {

    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    SemanticService semanticService;
    @Autowired
    MaDMPLoader maDMPLoader;
    @Autowired
    ShaclValidationService shaclValidationService;
    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    @GetMapping("validateShapeMadmp/{shape}/{madmp}")
    public ShaclValidationResult verifyShapeMadmp(@RequestParam(name="shape", defaultValue="shapes_02") String shape, @RequestParam(name="madmp", defaultValue="zenodo/11") String maDMP) throws IOException, TransformationException {
        var shapeFile = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape)).getFile();
        var shapeModel = semanticService.loadModelFromFile(shapeFile);

        var maDMPFile = resourcePatternResolver.getResource(String.format("classpath:/maDMPs/%s.json", maDMP)).getFile();
        var madmpModel = maDMPLoader.fromIdentifier(maDMPFile);
        var validation = shaclValidationService.validateShape(madmpModel, shapeModel);

        return new ShaclValidationResult(maDMPFile.getName(), shapeFile.getName(), validation);
    }

    @GetMapping("validateShape/{shape}")
    public List<ShaclValidationResult> verifyShape(@RequestParam(name = "shape", defaultValue = "shapes_02") String shape) throws IOException {
        var ressource = resourcePatternResolver.getResource(String.format("classpath:/original-evaluation/shacl_constraints/%s.ttl", shape));
        var shapeFile = ressource.getFile();
        var shapeModel = semanticService.loadModelFromFile(shapeFile);

        return Arrays.stream(resourcePatternResolver.getResources("classpath:/maDMPs/original_shacl_dmps/*.json")).map(element -> {
            try {
                var file = element.getFile();
                var madmpModel = maDMPLoader.fromIdentifier(file);
                var validation = shaclValidationService.validateShape(madmpModel, shapeModel);
                return new ShaclValidationResult(file.getName(), shapeFile.getName(), validation);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sorted(Comparator.comparing(ShaclValidationResult::getShape)).toList();
    }

    @GetMapping("validateMadmp/{madmp}")
    public List<ShaclValidationResult> verifyMaDMP(@RequestParam(name="madmp", defaultValue="zenodo/11") String maDMP) throws IOException, TransformationException {
        List<File> shapes = Arrays.stream(resourcePatternResolver.getResources("classpath:/original-evaluation/shacl_constraints/*.ttl")).map(element -> {
            try {
                return element.getFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        var maDMPFile = resourcePatternResolver.getResource(String.format("classpath:/maDMPs/%s.json", maDMP)).getFile();
        var maDmpModel = maDMPLoader.fromIdentifier(maDMPFile);

        return shapes.stream().map(x -> {
            var shapeModel = semanticService.loadModelFromFile(x);
            var validation = shaclValidationService.validateShape(maDmpModel, shapeModel);

            return new ShaclValidationResult(maDMPFile.getName(), x.getName(), validation);
        }).toList();
    }
}
