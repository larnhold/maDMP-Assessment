package org.arnhold.dmpeval.casestudy.context.fairSharing

import org.arnhold.sdk.contextLoader.ContextLoaderPlugin
import org.springframework.stereotype.Component

@Component
class FAIRSharingContextLoaderPlugin: ContextLoaderPlugin {

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