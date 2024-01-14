package org.arnhold.evaluator.dataProvision

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.evaluator.configuration.OntologyConfig
import org.arnhold.evaluator.dataProvision.contextProvider.ContextProviderService
import org.arnhold.evaluator.dataProvision.dmpProvider.DmpProviderService
import org.arnhold.evaluator.evaluationManager.DMPLoaderParameters
import org.arnhold.evaluator.tripleStore.TripleStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.FileInputStream
import java.nio.file.Path
import java.util.*

@Component
class DataProviderServiceImpl @Autowired constructor(
    val tripleStoreService: TripleStoreService,
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

    override fun getContextOntologies(): Map<String, OntModel> {
        TODO("Not yet implemented")
    }

    override fun loadDMP(dmploader: String, dmpIdentifier: String): Model {
        logger.info { "Load DMP $dmpIdentifier from DMPLoader $dmploader" }
        return dmpProviderService.loadDMP(dmploader, dmpIdentifier, getDCSOntology())
    }

    override fun loadContextualizedDMP(parameters: DMPLoaderParameters): UUID {
        val loadedDMP = loadDMP(parameters.dmpLoader, parameters.dmpIdentifier)
        return saveModel(loadedDMP)
    }

    override fun getContextualizedDMP(id: UUID): Model {
        logger.info { "Get DMP from store" }
        return tripleStoreService.getModel(id)
    }

    override fun updateStoredDMP(id: UUID, dmp: Model) {
        logger.info { "Update DMP in Store" }
        tripleStoreService.updateModel(id, dmp)
    }

    override fun saveModel(model: Model): UUID {
        logger.info { "Update model in store" }
        val storeId = UUID.randomUUID()
        //TODO save uuid in metadata store
        tripleStoreService.saveModel(storeId, model)
        return storeId
    }
}