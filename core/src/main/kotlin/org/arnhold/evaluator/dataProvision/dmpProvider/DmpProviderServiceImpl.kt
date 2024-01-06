package org.arnhold.evaluator.dataProvision.dmpProvider

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

    private fun getDmpLoader(identifier: String): DmpLoaderPlugin {
        return pluginLoader.getDMPLoader(identifier)
    }

    override fun loadDMP(dmploader: String, dmpIdentifier: String, dcsOntology: OntModel): Model {
        val loader = getDmpLoader(dmploader)
        val model: Model = loader.loadDMP(dmpIdentifier, dcsOntology)

        val reasoner: Reasoner = ReasonerRegistry.getOWLReasoner()
        reasoner.bindSchema(dcsOntology)

        return ModelFactory.createInfModel(reasoner, model)
    }
}