package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.Request
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.DmpLifecycle
import org.arnhold.sdk.common.dqv.Guidance
import org.arnhold.sdk.common.dqv.Measurement
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.tools.SparqlSelector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL
import java.nio.file.Path


@Component
class AvailabilityEvaluator @Autowired constructor(
    val sparqlSelector: SparqlSelector,
    val okHttpClient: OkHttpClient
) : DimensionEvaluatorPlugin {

    private val logger = KotlinLogging.logger {}

    companion object {
        const val SPARQL_DIRECTORY = "data/selectors/"
    }

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.AVAILABILITY.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.AVAILABILITY_DIMENSION,
            CategoryDimmensionModels.FEASABILITY_CATEGORY,
            listOf(
                FeasabilityMetricModels.ID_AVAILABLE_METRIC
            )
        )
    }

    override fun getAllMeasurements(dmp: Model, lifecycle: DataLifecycle): List<Measurement> {
        logger.info { "Get all availability measurements" }
        val allMeasurements = idEntityMeasurements(dmp) + licenseEntityMeasurements(dmp)
        return allMeasurements.filterNotNull()
    }

    private fun idEntityMeasurements(dmp: Model): List<Measurement?> {
        logger.info { "Get identity measurements" }
        val query = Path.of(SPARQL_DIRECTORY + "ids.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} IDs"}
        return selected.map {
            getAvailabilityMeasurement(
                it.resources.get("id"),
                isAvailable(it.literals.get("value").toString(), it.literals.get("type").toString()),
                FeasabilityMetricModels.ID_AVAILABLE_METRIC
            )
        }
    }

    private fun licenseEntityMeasurements(dmp: Model): List<Measurement?> {
        logger.info { "Get license measurements" }
        val query = Path.of(SPARQL_DIRECTORY + "licenses.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} licenses"}
        return selected.map {
            getAvailabilityMeasurement(
                it.resources.get("license"),
                isAvailable(it.literals.get("ref").toString(), ""),
                FeasabilityMetricModels.LICENSE_AVAILABLE_METRIC
            )
        }
    }

    private fun accessUrlEntityMeasurements(): List<Measurement> {
        logger.info { "Get access url measurements" }
        return listOf()
    }

    private fun downloadUrlEntityMeasurements(): List<Measurement> {
        logger.info { "Get download url measurements" }
        return listOf()
    }

    private fun hostUrlEntityMeasurements(): List<Measurement> {
        logger.info { "Get host url measurements" }
        return listOf()
    }

    private fun isAvailable(id: String, type: String): Boolean {
        logger.info { "Check if $id is available" }
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
            return httpCheck("http://doi.org/$id")
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

    private fun getAvailabilityMeasurement(computedOn: Resource?, available: Boolean, metric: Metric): Measurement? {
        return if (computedOn != null) {
            Measurement(DmpLifecycle(DataLifecycle.PUBLISHED), metric, Guidance("", ""), computedOn, available.toString())
        } else {
            null
        }
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}