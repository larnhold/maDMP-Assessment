package org.arnhold.dmpeval.casestudy.evaluation

import org.arnhold.sdk.model.EvaluationCategoryConstants
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.vocab.dqv.Category
import org.arnhold.sdk.vocab.dqv.Dimension

class CategoryDimmensionModels {
    companion object {
        // evaluators for marked dimensions

        val COMPLETENSS_CATEGORY = Category(null, EvaluationCategoryConstants.COMPLETENESS.toString())
        val FEASABILITY_CATEGORY = Category(null, EvaluationCategoryConstants.FEASABILITY.toString())
        val QUALITY_OF_ACTIONS_CATEGORY = Category(null, EvaluationCategoryConstants.QUALITY_OF_ACTIONS.toString())
        val COMPLIANCE_CATEGORY = Category(null, EvaluationCategoryConstants.COMPLIANCE.toString())

        //+1
        val DCS_COMPLETENESS_DIMENSION = Dimension(COMPLETENSS_CATEGORY, EvaluationDimensionConstants.DCS_COMPLETENESS.toString(), null)
        //+1
        val SCIENCE_EUROPE_EXTENSION_COMPLETENESS_DIMENSION = Dimension(COMPLETENSS_CATEGORY, EvaluationDimensionConstants.SCIENCE_EUROPE_EXTENSION_COMPLETENESS.toString(), null)
        //+1
        val ACCURACY_DIMENSION = Dimension(FEASABILITY_CATEGORY, EvaluationDimensionConstants.ACCURACY.toString(), null)
        //+1
        val AVAILABILITY_DIMENSION = Dimension(FEASABILITY_CATEGORY, EvaluationDimensionConstants.AVAILABILITY.toString(), null)
        //+1
        val CONSISTENCY_DIMENSION = Dimension(FEASABILITY_CATEGORY, EvaluationDimensionConstants.CONSISTENCY.toString(), null)

        val GUIDELINE_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.GUIDELINE_COMPLIANCE.toString(), null)
        //+1
        val DCS_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.DCS_COMPLIANCE.toString(), null, GUIDELINE_COMPLIANCE_DIMENSION)
        //+1
        val SCIENCE_EUROPE_COMPLIANCE_DIMENSION = Dimension(COMPLIANCE_CATEGORY, EvaluationDimensionConstants.SCIENCE_EUROPE_COMPLIANCE.toString(), null, GUIDELINE_COMPLIANCE_DIMENSION)

        //+1
        val FAIR_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.FAIR.toString(), null)

        val FINDABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.FINDABLE.toString(), null, FAIR_DIMENSION)
        val ACCESSIBLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.ACCESSIBLE.toString(), null, FAIR_DIMENSION)
        val INTEROPERABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.INTEROPERABLE.toString(), null, FAIR_DIMENSION)
        val REUSABLE_DIMENSION = Dimension(QUALITY_OF_ACTIONS_CATEGORY, EvaluationDimensionConstants.REUSABLE.toString(), null, FAIR_DIMENSION)

        // => 8 Dimensions
    }
}