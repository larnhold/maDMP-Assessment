package org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.context.schema.Dataset
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.vocab.constants.ContextSchema
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.re3data.schema._2_2.Re3Data.Repository
import org.springframework.stereotype.Component

@Component
class AccuracyDimensionEvaluator : DimensionEvaluatorPlugin {

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

        val measurements = mutableListOf<Measurement>()
        val re3dataContext = context.filter { it.vocabularyIdentifier === ContextSchema.HOST }
        val openAireContext = context.filter { it.vocabularyIdentifier === ContextSchema.DATASET }

        if (re3dataContext.isNotEmpty())  {
            measurements.addAll(fromRe3DataContext(dmp, re3dataContext))
        }

        if (openAireContext.isNotEmpty()) {
            measurements.addAll(fromOpenAireContext(dmpOntology, openAireContext))
        }

        return measurements
    }

    private fun fromOpenAireContext(dmp: Model, context: List<DMPContext>): List<Measurement> {
        return context.flatMap { mapSingleOpenAireContext(dmp, it) }
    }

    private fun mapSingleOpenAireContext(dmp: Model, context: DMPContext): List<Measurement> {
        if (context.value != null) {
            val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val openAireDataset = reader.readValue(context.value, Dataset::class.java)
            logger.info { "OpenAireDataset: $openAireDataset" }
        }

        return listOf()
    }

    private fun fromRe3DataContext(dmp: Model, context: List<DMPContext>): List<Measurement> {
        return context.flatMap { mapSingleRe3DataContext(dmp, it) }
    }

    private fun mapSingleRe3DataContext(dmp: Model, context: DMPContext): List<Measurement> {
        if (context.value != null) {
            val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val re3DataHost = reader.readValue(context.value, Repository::class.java)
            logger.info { "Re3DataHost: $re3DataHost" }
        }

        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.ACCURACY.toString() == p0
    }
}