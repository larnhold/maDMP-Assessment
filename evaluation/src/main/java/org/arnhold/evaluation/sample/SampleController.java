package org.arnhold.evaluation.sample;

import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.arnhold.evaluation.openaire.service.OpenAireService;
import org.arnhold.semantic.SemanticService;
import org.example.dcsojson.DcsoJsonTransformer;
import org.example.dcsojson.TransformationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    OpenAireService openAireService;

    @Autowired
    SemanticService semanticService;

    private Sample getSample() {
        Sample sample = new Sample();
        sample.setId("test");
        sample.setName("test");

        return sample;
    }

    @GetMapping("/{id}")
    public Sample findById(@PathVariable long id) {
        return getSample();
    }

    @GetMapping("/")
    public String test() throws IOException, TransformationException {
        Resource resource = resourceLoader.getResource("classpath:/maDMPs/zenodo/1.json");
        File file = resource.getFile();
        var transformer = new DcsoJsonTransformer();
        var model = transformer.convertPlainToModel(file);
        StringWriter sw = new StringWriter();
        RDFDataMgr.write(sw, model, Lang.TURTLE);

        openAireService.test(model);
        semanticService.test();

        return sw.toString();
    }
}
