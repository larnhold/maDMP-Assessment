package org.arnhold.evaluator.indicator.evaluationProvider

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import mu.KotlinLogging
import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.Measurement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : EvaluationProviderService {

    private val logger = KotlinLogging.logger {}

    override fun getAllEvaluators(): List<DimensionEvaluatorPlugin> {
        logger.info { "Get instance of all evaluators" }
        return pluginLoader.getEvaluators()
    }

    override suspend fun produceAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle ${parameters.dataLifecycle}" }
        return coroutineScope {
            return@coroutineScope getAllEvaluators().map { async { it.getAllMeasurements(dmp, context, parameters, dmpOntology, extensionOntologies) } }.awaitAll().flatten()
        }
    }

    override suspend fun produceMeasurementsForDimensions(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle ${parameters.dataLifecycle} and Dimensions ${parameters.dimensions}" }
        return coroutineScope {
            if (parameters.dimensions == null) {
                return@coroutineScope listOf()
            } else {
                val applicableEvaluators = getAllEvaluators()
                    .filter { evaluator ->
                        parameters.dimensions!!.map {dim -> dim.uppercase() }
                            .contains(evaluator.getPluginInformation().applicableDimension.title.uppercase())
                    }
                return@coroutineScope applicableEvaluators.map { async { it.getAllMeasurements(dmp, context, parameters, dmpOntology, extensionOntologies) } }.awaitAll().flatten()
            }
        }
    }

    override suspend fun produceMeasurementsForCategories(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        logger.info { "Produce all available metrics for lifecycle ${parameters.dataLifecycle} and Categories ${parameters.categories}" }
        return coroutineScope {
            if (parameters.categories == null) {
                return@coroutineScope listOf()
            } else {
                return@coroutineScope getAllEvaluators()
                    .filter { evaluator ->
                        parameters.dimensions!!.map {dim -> dim.uppercase() }
                            .contains(evaluator.getPluginInformation().belongsToCategory.title.uppercase())
                    }
                    .map { async { it.getAllMeasurements(dmp, context, parameters, dmpOntology, extensionOntologies) } }.awaitAll().flatten()
            }
        }
    }
}