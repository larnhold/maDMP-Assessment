package org.arnhold.evaluation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["org.arnhold"])
class EvaluationApplication

fun main(args: Array<String>) {
    runApplication<EvaluationApplication>(*args)
}

