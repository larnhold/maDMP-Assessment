package org.arnhold.evaluation.sample;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

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
    public Collection<Sample> findBooks() {
        return List.of(getSample());
    }
}
