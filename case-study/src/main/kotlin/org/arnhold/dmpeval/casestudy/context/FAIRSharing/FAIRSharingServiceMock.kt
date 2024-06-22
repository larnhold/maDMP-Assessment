package org.arnhold.dmpeval.casestudy.context.FAIRSharing

import org.springframework.stereotype.Component

@Component
class FAIRSharingServiceMock: FAIRSharingService {

    companion object{
        val STANDARS = listOf("DOI", "ORCID")
        val DATABASES = listOf("GITHUB", "ZENODO")
    }

    override fun isStandard(value: String): Boolean {
        return STANDARS.any {x -> x.contains(value, ignoreCase = true) }
    }

    override fun isDatabase(value: String): Boolean {
        return DATABASES.any {x -> x.contains(value, ignoreCase = true) }
    }
}