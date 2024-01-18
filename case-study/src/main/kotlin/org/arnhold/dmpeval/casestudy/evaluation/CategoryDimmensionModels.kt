package org.arnhold.dmpeval.casestudy.evaluation

import org.arnhold.sdk.common.dqv.Category
import org.arnhold.sdk.common.dqv.Dimension

class CategoryDimmensionModels {
    companion object {
        val COMPLETENSS_CATEGORY = Category("", EvaluationCategoryConstants.COMPLETENESS.toString())
        val FEASABILITY_CATEGORY = Category("", EvaluationCategoryConstants.FEASABILITY.toString())
        val QUALITY_OF_ACTIONS_CATEGORY = Category("", EvaluationCategoryConstants.QUALITY_OF_ACTIONS.toString())
        val COMPLIANCE_CATEGORY = Category("", EvaluationCategoryConstants.COMPLIANCE.toString())

        val DCS_COMPLETENESS_DIMENSION = Dimension(COMPLETENSS_CATEGORY, EvaluationDimensionConstants.DCS_COMPLETENESS.toString(), "")
        val EXTENSION_COMPLETENESS_DIMENSION = Dimension(COMPLETENSS_CATEGORY, EvaluationDimensionConstants.EXTENSION_COMPLETENESS.toString(), "")
        val ACCURACY_DIMENSION = Dimension(FEASABILITY_CATEGORY, EvaluationDimensionConstants.ACCURACY.toString(), "")
        val AVAILABILITY_DIMENSION = Dimension(FEASABILITY_CATEGORY, EvaluationDimensionConstants.AVAILABILITY.toString(), "")
        val GUIDELINE_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.GUIDELINE_COMPLIANCE.toString(), "")
        val DCS_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.DCS_COMPLIANCE.toString(), "")
        val EXTENSION_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.EXTENSION_COMPLIANCE.toString(), "")

        val FAIR_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.FAIR.toString(), "")

        val FINDABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.FINDABLE.toString(), "", FAIR_DIMENSION)
        val ACCESSIBLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.ACCESSIBLE.toString(), "", FAIR_DIMENSION)
        val INTEROPERABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.INTEROPERABLE.toString(), "", FAIR_DIMENSION)
        val REUSABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.REUSABLE.toString(), "", FAIR_DIMENSION)
    }
}