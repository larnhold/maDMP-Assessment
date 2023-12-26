package org.arnhold.evaluation.openaire;

import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openaire")
public class OpenAireController {

    @Autowired
    OpenAireService openAireService;

    // https://pidforum.org/t/pids-in-openaire/1747/5

    @GetMapping("test")
    public String test() {
        return openAireService.test(ModelFactory.createDefaultModel());
    }

}
