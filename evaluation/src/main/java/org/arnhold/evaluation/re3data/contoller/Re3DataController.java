package org.arnhold.evaluation.re3data.contoller;

import org.arnhold.evaluation.openaire.model.Re3Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openaire")
public class Re3DataController {

    @GetMapping("test")
    public Re3Data test() {
        System.out.println("");
        return new Re3Data();
    }

}

