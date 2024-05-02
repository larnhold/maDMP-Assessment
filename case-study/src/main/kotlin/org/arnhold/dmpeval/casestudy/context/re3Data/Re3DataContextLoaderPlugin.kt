package org.arnhold.dmpeval.casestudy.context.re3Data

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.stereotype.Component

@Component
class Re3DataContextLoaderPlugin: ContextLoaderPlugin {

    override fun getPluginIdentifier(): String {
        return ContextLoaderIdentifier.RE3DATA.toString()
    }

    override fun getPluginInformation(): ContextProviderInformation {
        TODO("Not yet implemented")
    }

    override fun getContext(dmpModel: Model): DMPContext {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        return p0 == ContextLoaderIdentifier.RE3DATA.toString()
    }
}