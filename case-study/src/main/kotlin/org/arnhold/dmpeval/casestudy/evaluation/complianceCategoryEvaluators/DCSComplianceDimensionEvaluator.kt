package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

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
class DCSComplianceDimensionEvaluator @Autowired constructor(
    val shaclValidationService: ShaclValidationService
) : DimensionEvaluatorPlugin {

    val dcsMultiplicityShapes: Path = Path.of("./data/case-study/shapes/dcs-multiplicity.ttl")
    val dateISOPatternShape: Path = Path.of("./data/case-study/shapes/dcs-date.ttl")

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.DCS_COMPLIANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.DCS_COMPLIANCE_DIMENSION,
            CategoryDimmensionModels.COMPLIANCE_CATEGORY,
            listOf(DCSComplianceMetricModels.DCS_MULTIPLICITY_METRIC, DCSComplianceMetricModels.DCS_WHITELIST_METRIC)
        )
    }

    override fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        val measurements = mutableListOf<Measurement>()
        measurements.addAll(getMultiplicityComplianceMeasurements(dmp, DmpLifecycle(parameters.dataLifecycle)))
        measurements.addAll(getDateISOMeasurements(dmp, DmpLifecycle(parameters.dataLifecycle)))
        return measurements
    }

    fun getMultiplicityComplianceMeasurements(dmp: Model, lifecycle: DmpLifecycle): List<Measurement> {
        return shaclValidationService.validateShape(
            dmp,
            dcsMultiplicityShapes,
            DCSComplianceMetricModels.DCS_MULTIPLICITY_METRIC,
            DMPLocation("dmp", ""),
            lifecycle
        )
    }

    fun getDateISOMeasurements(dmp: Model, lifecycle: DmpLifecycle): List<Measurement> {
        return shaclValidationService.validateShape(
            dmp,
            dateISOPatternShape,
            DCSComplianceMetricModels.DCS_VALUE_PATTERN_CONTRAINT_METRIC,
            DMPLocation("dmp", ""),
            lifecycle
        )
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.DCS_COMPLIANCE.toString() == p0
    }
}