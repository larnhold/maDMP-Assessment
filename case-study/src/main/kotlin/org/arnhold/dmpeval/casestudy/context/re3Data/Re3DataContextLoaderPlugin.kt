package org.arnhold.dmpeval.casestudy.context.re3Data

import com.fasterxml.jackson.databind.ObjectWriter
import mu.KotlinLogging
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.ContextLoaderIdentifier
import org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators.AvailabilityDimensionEvaluator
import org.arnhold.sdk.context.ContextLoaderPlugin
import org.arnhold.sdk.context.ContextProviderInformation
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.constants.ContextSchema
import org.arnhold.sdk.vocab.context.ContextDMPLocation
import org.arnhold.sdk.vocab.context.DMPContext
import org.re3data.schema._2_2.Re3Data.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class Re3DataContextLoaderPlugin @Autowired constructor(
    val sparqlSelector: SparqlSelector,
    val objectWriter: ObjectWriter,
    private val re3DataService: Re3DataService
) : ContextLoaderPlugin {

    private val logger = KotlinLogging.logger {}

    override fun getPluginIdentifier(): String {
        return ContextLoaderIdentifier.RE3DATA.toString()
    }

    override fun getPluginInformation(): ContextProviderInformation {
        return ContextProviderInformation()
    }

    override fun getContext(dmpModel: Model): List<DMPContext> {
        logger.info { "Get Re3Data context for all datasets" }
        val query = Path.of(AvailabilityDimensionEvaluator.SPARQL_DIRECTORY + "allHosts.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmpModel, query)
        logger.info { "Found ${selected.size} Datasets with identifiers"}

        return selected.map {
            val dmp = it.resources.get("dmp").toString()
            val name = it.literals.get("title").toString()
            val host = it.resources.get("host").toString()
            logger.info { "Requesting context for host $name" }
            val context = re3DataService.getHostByName(name)
            return@map packageIntoDMPContext(dmp, host, context)
        }
    }

    private fun packageIntoDMPContext(dmpId: String, hostId: String, context: Repository?): DMPContext {
        val location = ContextDMPLocation(dmpId, hostId, null)
        val jsonData = objectWriter.writeValueAsString(context)
        return DMPContext(location, ContextLoaderIdentifier.RE3DATA.toString(), jsonData, ContextSchema.HOST)
    }

    override fun supports(p0: String): Boolean {
        return p0 == ContextLoaderIdentifier.RE3DATA.toString()
    }
}