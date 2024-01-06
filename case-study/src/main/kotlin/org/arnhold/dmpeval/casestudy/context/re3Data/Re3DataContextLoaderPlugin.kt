package org.arnhold.dmpeval.casestudy.context.re3Data

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.springframework.stereotype.Component

@Component
class Re3DataContextLoaderPlugin: ContextLoaderPlugin {

    override fun getPluginIdentifier(): String {
        TODO("Not yet implemented")
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