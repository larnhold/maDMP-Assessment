package org.arnhold.evaluator.dataProvision

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api/data-provider")
class DataProviderController @Autowired constructor(
    val dataProviderService: DataProviderService
) {

    @GetMapping("dcso")
    fun getDCSOntology(): String {
        val test = dataProviderService.getDCSOntology();
        return ""
    }

}