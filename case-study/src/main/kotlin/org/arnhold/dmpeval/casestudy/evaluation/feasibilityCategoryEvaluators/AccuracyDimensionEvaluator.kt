package org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.*
import org.arnhold.dmpeval.casestudy.configuration.QueriesConfig
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.context.schema.Dataset
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.constants.ContextSchema
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Measurement
import org.re3data.schema._2_2.Re3Data.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path


@Component
class AccuracyDimensionEvaluator @Autowired constructor(
    val sparqlSelector: SparqlSelector,
    val queriesConfig: QueriesConfig
) : DimensionEvaluatorPlugin {

    private val logger = KotlinLogging.logger {}

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.ACCURACY.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.ACCURACY_DIMENSION,
            CategoryDimmensionModels.FEASIBILITY_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        logger.info { "Get all accuracy measurements" }

        val re3dataContext = context.filter { it.vocabularyIdentifier === ContextSchema.HOST }.filter { it.value !== null }.associateBy( {it}, {this.mapSingleRe3DataContext(it)} )
        val openAireContext = context.filter { it.vocabularyIdentifier === ContextSchema.DATASET }.filter { it.value !== null }.associateBy( {it}, {this.mapSingleOpenAireContext(it)} )

        return evaluateDatasetAccuracy(dmp, re3dataContext, openAireContext)
    }

    private fun evaluateDatasetAccuracy(dmp: Model, re3DataContext: Map<DMPContext, Repository>, openAireContext: Map<DMPContext, Dataset>): List<Measurement> {
        logger.info { "Select all datasets and corresponding information" }
        val query = Path.of(queriesConfig.directory + "/datasetInfo/allDatasetInformation.sparql").toFile().readText(Charsets.UTF_8)
        val selected = sparqlSelector.getSelectResults(dmp, query)
        logger.info { "Found ${selected.size} Datasets"}

        val measurements = mutableListOf<Measurement>()

        selected.forEach {
            val dataset = it.resources.get("dataset").toString()
            val description = it.literals.get("description").toString()
            val title = it.resources.get("title").toString()
            val keyword = it.resources.get("keyword").toString()
            val metadataLanguage = it.resources.get("metadataLanguage").toString()
        }

        return measurements
    }

    private fun mapSingleOpenAireContext(context: DMPContext): Dataset {
        val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return reader.readValue(context.value, Dataset::class.java)
    }

    private fun mapSingleRe3DataContext(context: DMPContext): Repository {
        val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return reader.readValue(context.value, Repository::class.java)
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.ACCURACY.toString() == p0
    }
}