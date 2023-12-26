package org.arnhold.dmpeval.casestudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["org.arnhold", "at.ac.tuwien"])
class CaseStudyApplication

fun main(args: Array<String>) {
    runApplication<CaseStudyApplication>(*args)
}
