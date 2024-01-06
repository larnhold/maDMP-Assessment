package org.arnhold.evaluator.dataProvision

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

    override fun getDCSOntology(): OntModel {
        val dcso = ModelFactory.createOntologyModel()
        val dcsoInputStream = FileInputStream(Path.of(ontologyConfig.DCSLocation).toFile())
        RDFDataMgr.read(dcso, dcsoInputStream, Lang.TURTLE)
        return dcso
    }

    override fun getExtensions(): Map<String, OntModel> {
        TODO("Not yet implemented")
    }

    override fun getContextOntologies(): Map<String, OntModel> {
        TODO("Not yet implemented")
    }

    override fun loadDMP(dmploader: String, dmpIdentifier: String): Model {
        return dmpProviderService.loadDMP(dmploader, dmpIdentifier, getDCSOntology())
    }

    override fun loadContextualizedDMP(parameters: DMPLoaderParameters): UUID {
        val loadedDMP = loadDMP(parameters.dmpLoader, parameters.dmpIdentifier)
        val dmpStoreId = UUID.randomUUID()
        //TODO save uuid in metadata store

        tripleStoreService.saveModel(dmpStoreId, loadedDMP)
        return dmpStoreId
    }

    override fun getContextualizedDMP(id: UUID): Model {
        return tripleStoreService.getModel(id)
    }
}