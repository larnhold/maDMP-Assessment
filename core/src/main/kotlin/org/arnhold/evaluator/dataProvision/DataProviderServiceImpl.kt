package org.arnhold.evaluator.dataProvision

import at.ac.tuwien.dcsojson.DcsoJsonTransformer
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.riot.Lang
import org.apache.jena.riot.RDFDataMgr
import org.arnhold.evaluator.dataProvision.contextProvider.ContextProviderService
import org.arnhold.evaluator.dataProvision.dmpProvider.DmpProviderService
import org.arnhold.evaluator.tripleStore.TripleStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class DataProviderServiceImpl @Autowired constructor(
    val tripleStoreService: TripleStoreService,
    val dmpProviderService: DmpProviderService,
    val contextProviderService: ContextProviderService
) : DataProviderService {

    companion object {
        const val ONTOLOGY_DCSO_TTL = "/ontology/dcso.ttl"
    }

    override fun getDCSOntology(): OntModel {
        val dcso = ModelFactory.createOntologyModel()
        RDFDataMgr.read(dcso, getResourceAsStream(DcsoJsonTransformer.ONTOLOGY_DCSO_TTL), Lang.TURTLE)
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

    private fun getResourceAsStream(resourcePath: String): InputStream? {
        return javaClass.getResourceAsStream(resourcePath)
    }
}