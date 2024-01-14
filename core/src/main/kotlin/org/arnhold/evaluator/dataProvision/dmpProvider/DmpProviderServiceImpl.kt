package org.arnhold.evaluator.dataProvision.dmpProvider

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.reasoner.Reasoner
import org.apache.jena.reasoner.ReasonerRegistry
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

    override fun loadDMP(dmploader: String, dmpIdentifier: String, dcsOntology: OntModel): Model {
        logger.info { "Load DMP $dmpIdentifier using $dmploader" }
        val loader = getDmpLoader(dmploader)
        val model: Model = loader.loadDMP(dmpIdentifier, dcsOntology)

        logger.info { "Reason over loaded DMP using DCS Ontology" }
        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(dcsOntology)

        return ModelFactory.createInfModel(reasoner, model)
    }
}