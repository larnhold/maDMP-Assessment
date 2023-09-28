package org.arnhold.evaluation.shacl;

import org.apache.jena.shacl.lib.ShLib;
import org.arnhold.semantic.SemanticService;
import org.arnhold.semantic.shacl.ShaclValidationService;
import org.example.dcsojson.DcsoJsonTransformer;
import org.example.dcsojson.TransformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/legacy")
public class OldShaclController {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    SemanticService semanticService;

    @Autowired
    DcsoJsonTransformer dcsoJsonTransformer;

    @Autowired
    ShaclValidationService shaclValidationService;


    @GetMapping("/")
    public Boolean verify() throws IOException, TransformationException {
        File maDMP = resourceLoader.getResource("classpath:/maDMPs/zenodo/1.json").getFile();
        var maDMPModel = dcsoJsonTransformer.convertPlainToModel(maDMP);

        File shapes = resourceLoader.getResource("classpath:/original-evaluation/shacl_constraints/shapes_01.ttl").getFile();
        var shapesModel = semanticService.loadModelFromFile(shapes);

        var report = shaclValidationService.validateShape(maDMPModel, shapesModel);
        return report.conforms();
    }
}
