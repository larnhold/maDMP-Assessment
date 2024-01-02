package org.arnhold.evaluator.tripleStore

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/triple-store")
class TripleStoreController @Autowired constructor(
    val tripleStoreService: TripleStoreService
) {

    @GetMapping("test")
    fun test() {
    }

}