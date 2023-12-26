package org.arnhold.casestudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.beans
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class CaseStudyApplication

fun main(args: Array<String>) {
    runApplication<CaseStudyApplication>(*args)
}
