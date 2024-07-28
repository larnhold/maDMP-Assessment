package org.arnhold.dmpeval.casestudy.evaluation.feasibilityCategoryEvaluators

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiRoot
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
        val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val re3dataContext = context.find { it.vocabularyIdentifier === ContextSchema.HOST }?.value
        val openAireContext = context.find { it.vocabularyIdentifier === ContextSchema.DATASET }?.value

        val re3DataHost = reader.readValue(re3dataContext, Repository::class.java)
        val openAireDataset = reader.readValue(openAireContext, Dataset::class.java)

        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}