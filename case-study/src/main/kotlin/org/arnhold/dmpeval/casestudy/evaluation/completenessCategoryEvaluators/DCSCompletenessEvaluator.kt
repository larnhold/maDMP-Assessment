package org.arnhold.dmpeval.casestudy.evaluation.completenessCategoryEvaluators

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.model.CategoryDimmensionModels
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.tools.shacl.ShaclValidationService
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.DMPLocation
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class DCSCompletenessEvaluator @Autowired constructor(
    val shaclValidationService: ShaclValidationService
) : EvaluatorPlugin {

    val dcsCompletenessShapes: Path = Path.of("./data/case-study/shapes/dcs-completeness.ttl")

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.DCS_COMPLETENESS.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.DCS_COMPLETENESS_DIMENSION,
            CategoryDimmensionModels.COMPLETENSS_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<String, OntModel>
    ): List<Measurement> {
        return getCompletenessValuesMeasurements(dmp, DmpLifecycle(parameters.dataLifecycle))
    }

    fun getCompletenessValuesMeasurements(dmp: Model, lifecycle: DmpLifecycle): List<Measurement> {
        return shaclValidationService.validateShape(
            dmp,
            dcsCompletenessShapes,
            CompletenessMetricModels.DCS_COMPLETENESS_METRIC,
            DMPLocation(dmp.toString(), null),
            lifecycle
        )
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.DCS_COMPLETENESS.toString() == p0
    }
}