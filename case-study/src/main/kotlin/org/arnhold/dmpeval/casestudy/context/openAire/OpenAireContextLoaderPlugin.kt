package org.arnhold.dmpeval.casestudy.context.openAire

import com.fasterxml.jackson.databind.ObjectWriter
import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.configuration.DataStoreConfig
import org.arnhold.dmpeval.casestudy.configuration.QueriesConfig
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.arnhold.sdk.context.schema.Dataset
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.constants.ContextSchema
import org.arnhold.sdk.vocab.context.ContextDMPLocation
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class OpenAireContextLoaderPlugin @Autowired constructor(
    val sparqlSelector: SparqlSelector,
    val openAireService: OpenAireService,
    val objectWriter: ObjectWriter,
    val queriesConfig: QueriesConfig
) : ContextLoaderPlugin {

    private val logger = KotlinLogging.logger {}

    override fun getPluginIdentifier(): String {
        return ContextLoaderIdentifier.OPEN_AIRE.toString()
    }

    override fun getPluginInformation(): ContextProviderInformation {
        return ContextProviderInformation()
    }

    override fun getContext(dmpModel: Model): List<DMPContext> {
        logger.info { "Get OpenAire context for all datasets" }
        val query = Path.of(queriesConfig.directory + "/allDatasets.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmpModel, query)
        logger.info { "Found ${selected.size} Datasets with identifiers"}

        return selected.filter {
            val idType = it.literals.get("idType").toString()
            return@filter idType.lowercase() == "doi"
        }.map {
            val dmp = it.resources.get("dmp").toString()
            val id = it.literals.get("idValue").toString()
            val dataset = it.resources.get("dataset").toString()
            logger.info { "Requesting context for dataset $dataset" }
            val context = openAireService.findDatasetByDoi(id)
            return@map packageIntoDMPContext(dmp, dataset, context)
        }
    }

    private fun packageIntoDMPContext(dmpId: String, dataSetId: String, context: Dataset?): DMPContext {
        val location = ContextDMPLocation(dmpId, dataSetId, null)
        val jsonData = objectWriter.writeValueAsString(context)
        return DMPContext(listOf(location), ContextLoaderIdentifier.OPEN_AIRE.toString(), jsonData, ContextSchema.DATASET)
    }

    override fun supports(p0: String): Boolean {
        return p0 == ContextLoaderIdentifier.OPEN_AIRE.toString()
    }
}