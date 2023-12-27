package org.arnhold.evaluator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["org.arnhold"])
class EvaluatorApplication

fun main(args: Array<String>) {
    runApplication<EvaluatorApplication>(*args)
}
