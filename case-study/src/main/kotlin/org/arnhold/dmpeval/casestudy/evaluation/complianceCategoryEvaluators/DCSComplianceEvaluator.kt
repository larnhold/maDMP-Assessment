package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.Measurement
import org.arnhold.sdk.evaluator.EvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.tools.shacl.ShaclValidationService
import org.arnhold.sdk.vocab.context.DMPContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class DCSComplianceEvaluator @Autowired constructor(
    val shaclValidationService: ShaclValidationService
) : EvaluatorPlugin {

    val allowedValueLocation: Path = Path.of("./data/case-study/shapes/dcs-whitelist.shacl")

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.DCS_COMPLIANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.DCS_COMPLIANCE_DIMENSION,
            CategoryDimmensionModels.COMPLIANCE_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(dmp: Model, context: List<DMPContext>, lifecycle: DataLifecycle): List<Measurement> {
        return listOf()
    }

    override fun supports(p0: String): Boolean {
        return true
    }
}