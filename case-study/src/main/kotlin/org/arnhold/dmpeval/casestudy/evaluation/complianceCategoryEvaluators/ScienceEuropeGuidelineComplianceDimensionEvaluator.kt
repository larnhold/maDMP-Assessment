package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.FAIRSharing.FAIRSharingService
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ScienceEuropeGuidelineComplianceDimensionEvaluator @Autowired constructor(
    val fairSharingService: FAIRSharingService
) : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE_DIMENSION,
            CategoryDimmensionModels.COMPLIANCE_CATEGORY,
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
        return if (extensionOntologies.containsKey(Extension.SCIENCE_EUROPE)) {
            produceMeasurements()
        } else {
            listOf()
        }
    }

    private fun produceMeasurements(): List<Measurement> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE.toString() == p0
    }
}