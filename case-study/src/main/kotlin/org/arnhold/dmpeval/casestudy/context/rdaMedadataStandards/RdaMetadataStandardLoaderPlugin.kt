package org.arnhold.dmpeval.casestudy.context.rdaMedadataStandards

import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.springframework.stereotype.Component

@Component
class RdaMetadataStandardLoaderPlugin: ContextLoaderPlugin {

    override fun addContext(dmpModel: Model) {
        TODO("Not yet implemented")
    }

    override fun getIdentifier(): String {
        return "rda-metadata-standards"
    }

    override fun getRequiredConfigurationProperties(): List<String> {
        TODO("Not yet implemented")
    }

    override fun supports(p0: String): Boolean {
        TODO("Not yet implemented")
    }
}