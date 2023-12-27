package org.arnhold.dmpeval.casestudy.context.openAire

import org.apache.jena.rdf.model.Model

interface OpenAireService {
    fun test(madmp: Model): String

    fun getPublications(madmp: Model)
    fun getResearchData(madmp: Model): String
    fun getResearchSoftware(madmp: Model)
    fun getResearchProducts(madmp: Model)
}
