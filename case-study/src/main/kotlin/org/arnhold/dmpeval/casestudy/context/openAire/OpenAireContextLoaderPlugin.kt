package org.arnhold.dmpeval.casestudy.context.openAire

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.arnhold.sdk.vocab.context.ContextResult
import org.springframework.stereotype.Component

@Component
class OpenAireContextLoaderPlugin: ContextLoaderPlugin {

    override fun getPluginIdentifier(): String {
        return ContextLoaderIdentifier.OPEN_AIRE.toString()
    }

    override fun getPluginInformation(): ContextProviderInformation {
        TODO("Not yet implemented")
    }

    override fun getContext(dmpModel: Model): ContextResult {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        return p0 == ContextLoaderIdentifier.OPEN_AIRE.toString()
    }
}