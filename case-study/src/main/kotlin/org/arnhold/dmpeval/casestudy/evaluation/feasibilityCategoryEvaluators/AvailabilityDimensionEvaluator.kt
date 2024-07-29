package org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators

import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.commons.validator.routines.UrlValidator
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.dmpeval.casestudy.configuration.QueriesConfig
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.model.SoftareAgents
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL
import java.nio.file.Path


@Component
class AvailabilityDimensionEvaluator @Autowired constructor(
    val sparqlSelector: SparqlSelector,
    val queryConfig: QueriesConfig,
    val okHttpClient: OkHttpClient,
    val urlValidator: UrlValidator
) : DimensionEvaluatorPlugin {

    private val logger = KotlinLogging.logger {}

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.AVAILABILITY.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.AVAILABILITY_DIMENSION,
            CategoryDimmensionModels.FEASIBILITY_CATEGORY,
            listOf(
                FeasibilityMetricModels.LINKED_RESOURCE_EXISTENCE_METRIC
            )
        )
    }

    override fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        logger.info { "Get all availability measurements" }
        val allMeasurements = getAllIdentifiermeasurements(dmp) + allURIsMeasurements(dmp)
        logger.info { "All Availability measurements calculated: ${allMeasurements.size} results" }
        return allMeasurements.filterNotNull()
    }

    private fun allURIsMeasurements(dmp: Model): List<Measurement?> {
        logger.info { "Get measurements of al urls" }
        val query = Path.of(queryConfig.directory + "allUris.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} URIs in DMP"}

        return selected.mapNotNull {
            val subject = it.resources.get("subject")
            val verb = it.resources.get("verb")
            val urlValue = it.literals.get("value").toString()

            if (verb != null) {
                val measurement = getAvailabilityMeasurement(
                    urlValue,
                    subject,
                    verb,
                    httpCheck(urlValue),
                    FeasibilityMetricModels.LINKED_RESOURCE_EXISTENCE_METRIC
                )

                return@mapNotNull measurement
            } else {
                return@mapNotNull null
            }
        }
    }

    private fun getAllIdentifiermeasurements(dmp: Model): List<Measurement?> {
        logger.info { "Get measurements of al identifiers" }
        val query = Path.of(queryConfig.directory + "ids.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} IDs"}
        return selected.map {
            getAvailabilityMeasurement(
                it.literals.get("value").toString(),
                it.resources.get("root"),
                it.resources.get("id"),
                isIDAvailable(it.literals.get("value").toString(), it.literals.get("type").toString()),
                FeasibilityMetricModels.LINKED_RESOURCE_EXISTENCE_METRIC
            )
        }
    }

    private fun isIDAvailable(id: String, type: String): Boolean {
        logger.info { "Check if $id is available" }

        if (id.substring(0, 4) == "http") {
            return httpCheck(id)
        }

        return when (IdType.from(type)) {
            IdType.DOI -> doiCheck(id)
            IdType.ORCID -> orcidCheck(id)
            else -> {
                httpCheck(id)
            }
        }
    }

    private fun doiCheck(id: String): Boolean {
        logger.info { "Check doi of $id"}
        try {
            URL(id)
            return httpCheck(id)
        } catch (e: Exception) {
            return httpCheck("https://doi.org/$id")
        }
    }

    private fun orcidCheck(id: String): Boolean {
        logger.info { "Check orcid of $id"}
        try {
            URL(id)
            return httpCheck(id)
        } catch (e: Exception) {
            return httpCheck("https://orcid.org/$id")
        }
    }

    private fun httpCheck(urlString: String): Boolean {
        logger.info { "Check response of $urlString"}

        if (!urlValidator.isValid(urlString)) {
            logger.info { "Invalid url $urlString" }
            return false
        }

        val request = Request.Builder()
            .url(urlString)
            .build()

        return okHttpClient.newCall(request).execute().use { response ->
            logger.info { "$urlString HTTP ${response.code}" }
            if (response.code == 200) {
                return@use true
            } else {
                logger.info {  }
                return@use false
            }
        }
    }

    private fun getAvailabilityMeasurement(url: String, entity: Resource?, property: Resource?, available: Boolean, metric: Metric): Measurement {
        val metricCopy = metric.copy()
        metricCopy.description += ": $url"

        return Measurement(
            DmpLifecycle(DataLifecycle.PUBLISHED),
            metricCopy,
            null,
            DMPLocation(entity, property),
            available.toString(),
            softwareAgent= SoftareAgents.DMPEVAL,
        )
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}