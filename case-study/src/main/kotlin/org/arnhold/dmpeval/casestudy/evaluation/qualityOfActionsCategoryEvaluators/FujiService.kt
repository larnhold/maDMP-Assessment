package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiRoot


interface FujiService {
    fun evaluateResource(resource: String): FujiRoot?
}