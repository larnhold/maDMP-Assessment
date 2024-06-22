package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.tools.shacl.ShaclValidationService
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.DMPLocation
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class DCSCompletenessDimensionEvaluator @Autowired constructor(
    val shaclValidationService: ShaclValidationService
) : DimensionEvaluatorPlugin {

    val dcsCompletenessShapes: Path = Path.of("./data/case-study/shapes/dcs-completeness.ttl")

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.DCS_COMPLETENESS.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.DCS_COMPLETENESS_DIMENSION,
            CategoryDimmensionModels.COMPLETENSS_CATEGORY,
            listOf(
                DCS_CompletenessMetricModels.REQUIRED_ENTITY_OR_PROPERTY_EXISTENT_METRIC
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
        return getCompletenessValuesMeasurements(dmp, DmpLifecycle(parameters.dataLifecycle))
    }

    fun getCompletenessValuesMeasurements(dmp: Model, lifecycle: DmpLifecycle): List<Measurement> {
        return shaclValidationService.validateShape(
            dmp,
            dcsCompletenessShapes,
            DCS_CompletenessMetricModels.REQUIRED_ENTITY_OR_PROPERTY_EXISTENT_METRIC,
            DMPLocation("dmp", null),
            lifecycle
        )
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.DCS_COMPLETENESS.toString() == p0
    }
}