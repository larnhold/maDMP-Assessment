package org.arnhold.dmpeval.casestudy.context.orcid

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.springframework.stereotype.Component

@Component
class OrcidContextLoaderPlugin: ContextLoaderPlugin {

    override fun getIdentifier(): String {
        return ContextLoaderIdentifier.ORCID.toString()
    }

    override fun addContext(dmpModel: Model) {
        TODO("Not yet implemented")
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}