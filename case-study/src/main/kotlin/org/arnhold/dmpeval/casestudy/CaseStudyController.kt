package org.arnhold.dmpeval.casestudy

import org.arnhold.dmpeval.casestudy.context.openAire.OpenAireService
import org.arnhold.dmpeval.casestudy.context.re3Data.Re3DataService
import org.arnhold.sdk.context.schema.Dataset
import org.re3data.schema._2_2.Re3Data.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/case-study")
class CaseStudyController @Autowired constructor(
    val openaireService: OpenAireService,
    val re3DataService: Re3DataService
) {

    @GetMapping("getDataSet")
    fun getOpenAireDataset(): Dataset? {
        return openaireService.findDatasetByDoi("10.5281/zenodo.4662040")
    }

    @GetMapping("getRepository")
    fun getRe3DataRepository(): Repository? {
        return re3DataService.getHostByName("Zenodo")
    }

}