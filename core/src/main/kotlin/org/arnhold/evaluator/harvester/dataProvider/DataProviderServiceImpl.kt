package org.arnhold.evaluator.harvester.dataProvider

import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.evaluator.configuration.ExtensionConfig
import org.arnhold.evaluator.configuration.OntologyConfig
import org.arnhold.evaluator.configuration.OntologyInfo
import org.arnhold.evaluator.harvester.contextProvider.ContextProviderService
import org.arnhold.evaluator.harvester.dmpProvider.DmpProviderService
import org.arnhold.evaluator.harvester.inferenceEngine.InferenceEngineService
import org.arnhold.sdk.model.DMPLoaderParameters
import org.arnhold.sdk.store.DataStoreService
import org.arnhold.sdk.vocab.constants.Extension
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
    val ontologyConfig: OntologyConfig,
    val extensionConfig: ExtensionConfig,
    val inferenceEngineService: InferenceEngineService
) : DataProviderService {

    private val logger = KotlinLogging.logger {}

    override fun getDCSOntology(): OntModel {
        logger.info { "Load DCS Ontology" }
        val dcso = ModelFactory.createOntologyModel()
        val dcsoInputStream = FileInputStream(Path.of(ontologyConfig.DCSLocation).toFile())
        RDFDataMgr.read(dcso, dcsoInputStream, Lang.TURTLE)
        return dcso
    }

    override suspend fun loadContext(model: Model): List<DMPContext> {
        return contextProviderService.getAvailableContext(model)
    }

    override fun getDMPDQVOntology(): OntModel {
        logger.info { "Load DMPDQV Ontology" }
        val dmpdqv = ModelFactory.createOntologyModel()
        val dmpdqvInputStream = FileInputStream(Path.of(ontologyConfig.DMPDQVLocation).toFile())
        RDFDataMgr.read(dmpdqv, dmpdqvInputStream, Lang.TURTLE)
        return dmpdqv
    }

    override fun getExtensions(): Map<Extension, OntModel> {
        logger.info { "Load Extensions Ontologies" }
        val extensionInfo: List<OntologyInfo> = extensionConfig.ontologies
        return extensionInfo.associate { item ->
            val model = ModelFactory.createOntologyModel()
            val extensionInputStream = FileInputStream(Path.of(item.location).toFile())
            RDFDataMgr.read(model, extensionInputStream, Lang.TURTLE)

            return@associate Pair(item.name, model)
        }
    }

    private fun getCombinedDCSExtensionOntology(): OntModel {
        val dcs = getDCSOntology()
        val extensions = getExtensions()
        extensions.forEach { dcs.add(it.value) }
        return dcs
    }

    override fun loadDMP(parameters: DMPLoaderParameters): UUID {
        logger.info { "Load DMP $parameters.dmpIdentifier from DMPLoader $parameters.dmploader" }
        val ontology = getCombinedDCSExtensionOntology()
        val loadedDMP = dmpProviderService.loadDMP(parameters.dmpLoader, parameters.dmpIdentifier, ontology)
        val inferredDMP = inferenceEngineService.applyReasoning(ontology, loadedDMP)
        return saveModel(inferredDMP)
    }

    override fun getModel(id: UUID): Model {
        logger.info { "Get DMP from store" }
        return dataStoreService.getModel(id)
    }

    override fun saveModel(model: Model): UUID {
        logger.info { "Save model in store" }
        val storeId = UUID.randomUUID()
        dataStoreService.saveModel(storeId, model)
        return storeId
    }

    override fun <T> saveAsJson(data: Any, uuid: UUID?): UUID {
        logger.info { "Save model as JSON" }
        val storeId: UUID = uuid ?: UUID.randomUUID()
        dataStoreService.saveAsJson<T>(storeId, data)
        return storeId
    }
}