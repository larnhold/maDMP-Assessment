package org.arnhold.evaluator.dataProvision.dmpProvider

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dmpProvider")
class DmpProviderController {

    @GetMapping("")
    fun getRegisteredDmpProviders(): String {
        return ""
    }

    @GetMapping("{identifier}/config")
    fun getDmpProviderConfig(@RequestParam(name = "identifier") identifier: String): String {
        return ""
    }

}