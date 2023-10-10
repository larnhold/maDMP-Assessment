package org.arnhold.evaluation.openaire.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openaire")
public class OpenAireController {

    // https://pidforum.org/t/pids-in-openaire/1747/5

    @GetMapping("test")
    public void test() {
        System.out.println("");
    }

}
