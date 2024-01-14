package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.dqv.Metric

class FeasabilityMetricModels {
    companion object {
        val ID_AVAILABLE_METRIC = Metric("Avilability of an id", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DataLifecycle.PUBLISHED))
        val LICENSE_AVAILABLE_METRIC = Metric("Avilability of a license", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DataLifecycle.PUBLISHED))
        val ACCESS_URL_AVAILABLE_METRIC = Metric("Avilability of an access url", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DataLifecycle.PUBLISHED))
        val DOWNLOAD_URL_AVAILABLE_METRIC = Metric("Avilability of a download url", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DataLifecycle.PUBLISHED))
        val HOST_URL_AVAILABLE_METRIC = Metric("Avilability of a host url", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DataLifecycle.PUBLISHED))
    }
}