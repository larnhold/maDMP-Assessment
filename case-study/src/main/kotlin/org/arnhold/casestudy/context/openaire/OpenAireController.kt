package org.arnhold.casestudy.context.openaire

import org.apache.jena.rdf.model.ModelFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/openaire")
class OpenAireController {
    @Autowired
    var openAireService: OpenAireService? = null

    // https://pidforum.org/t/pids-in-openaire/1747/5
    @GetMapping("test")
    fun test(): String? {
        return openAireService!!.test(ModelFactory.createDefaultModel())
    }
}
