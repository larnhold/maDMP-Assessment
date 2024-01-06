package org.arnhold.dmpeval.casestudy.context.fairSharing

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.springframework.stereotype.Component

@Component
class FAIRSharingContextLoaderPlugin: ContextLoaderPlugin {

    override fun addContext(dmpModel: Model) {
        TODO("Not yet implemented")
    }

    override fun getIdentifier(): String {
        return "fairsharing"
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}