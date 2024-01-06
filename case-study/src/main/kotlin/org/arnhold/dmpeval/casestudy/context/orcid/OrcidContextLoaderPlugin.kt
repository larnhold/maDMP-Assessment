package org.arnhold.dmpeval.casestudy.context.orcid

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.springframework.stereotype.Component

@Component
class OrcidContextLoaderPlugin: ContextLoaderPlugin {

    override fun getPluginIdentifier(): String {
        return ContextLoaderIdentifier.ORCID.toString()
    }

    override fun getPluginInformation(): ContextProviderInformation {
        TODO("Not yet implemented")
    }

    override fun addContext(dmpModel: Model) {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}