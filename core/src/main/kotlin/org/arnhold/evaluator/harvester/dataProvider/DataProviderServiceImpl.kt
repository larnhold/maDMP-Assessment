package org.arnhold.evaluator.harvester.dataProvider

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.evaluator.configuration.OntologyConfig
import org.arnhold.evaluator.harvester.contextProvider.ContextProviderService
import org.arnhold.evaluator.harvester.dmpProvider.DmpProviderService
import org.arnhold.evaluator.indicator.evaluationManager.DMPLoaderParameters
import org.arnhold.sdk.store.DataStoreService
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.nio.file.Path
import java.util.*

@Component
class DataProviderServiceImpl @Autowired constructor(
    val dataStoreService: DataStoreService,
    val dmpProviderService: DmpProviderService,
    val contextProviderService: ContextProviderService,
    val ontologyConfig: OntologyConfig
) : DataProviderService {

    private val logger = KotlinLogging.logger {}

    override fun getDCSOntology(): OntModel {
        logger.info { "Load DCS Ontology" }
        val dcso = ModelFactory.createOntologyModel()
        val dcsoInputStream = FileInputStream(Path.of(ontologyConfig.DCSLocation).toFile())
        RDFDataMgr.read(dcso, dcsoInputStream, Lang.TURTLE)
        return dcso
    }

    override fun loadContext(model: Model): List<DMPContext> {
        return contextProviderService.getAvailableContext(model)
    }

    override fun getDMPDQVOntology(): OntModel {
        logger.info { "Load DMPDQV Ontology" }
        val dmpdqv = ModelFactory.createOntologyModel()
        val dmpdqvInputStream = FileInputStream(Path.of(ontologyConfig.DMPDQVLocation).toFile())
        RDFDataMgr.read(dmpdqv, dmpdqvInputStream, Lang.TURTLE)
        return dmpdqv
    }

    override fun getExtensions(): Map<String, OntModel> {
        TODO("Not yet implemented")
    }

    override fun loadDMP(parameters: DMPLoaderParameters): UUID {
        logger.info { "Load DMP $parameters.dmpIdentifier from DMPLoader $parameters.dmploader" }
        val loadedDMP = dmpProviderService.loadDMP(parameters.dmpLoader, parameters.dmpIdentifier, getDCSOntology())
        return saveModel(loadedDMP)
    }

    override fun getDMP(id: UUID): Model {
        logger.info { "Get DMP from store" }
        return dataStoreService.getModel(id)
    }

    override fun saveModel(model: Model): UUID {
        logger.info { "Update model in store" }
        val storeId = UUID.randomUUID()
        dataStoreService.saveModel(storeId, model)
        return storeId
    }

    override fun saveAsJson() {
    }
}