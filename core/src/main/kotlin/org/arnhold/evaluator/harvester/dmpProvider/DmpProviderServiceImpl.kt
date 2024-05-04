package org.arnhold.evaluator.harvester.dmpProvider

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.dmpLoader.DmpLoaderPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class DmpProviderServiceImpl @Autowired constructor(
    val pluginLoader: PluginLoader
) : DmpProviderService {

    private val logger = KotlinLogging.logger {}

    private fun getDmpLoader(identifier: String): DmpLoaderPlugin {
        logger.info { "Get instance of DMP loader $identifier" }
        return pluginLoader.getDMPLoader(identifier)
    }

    override fun loadDMP(dmploader: String, dmpIdentifier: String, ontology: OntModel): Model {
        logger.info { "Load DMP $dmpIdentifier using $dmploader" }
        val loader = getDmpLoader(dmploader)
        return loader.loadDMP(dmpIdentifier, ontology)
    }
}