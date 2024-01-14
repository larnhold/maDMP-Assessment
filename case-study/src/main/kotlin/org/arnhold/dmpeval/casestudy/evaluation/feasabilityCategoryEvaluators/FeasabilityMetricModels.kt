package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.common.constants.DataLifecycle
import org.arnhold.sdk.common.constants.MetricType
import org.arnhold.sdk.common.dqv.DmpLifecycle
import org.arnhold.sdk.common.dqv.Metric

class FeasabilityMetricModels {
    companion object {
        val ID_AVAILABLE_METRIC = Metric("Avilability of an id", "IdAvailable", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DmpLifecycle(DataLifecycle.PUBLISHED)), XSD.xboolean, MetricType.CHECKING)
        val LICENSE_AVAILABLE_METRIC = Metric("Avilability of a license", "LicenseAvailable", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DmpLifecycle(DataLifecycle.PUBLISHED)), XSD.xboolean, MetricType.CHECKING)
        val ACCESS_URL_AVAILABLE_METRIC = Metric("Avilability of an access url", "AccessUrlAvailable", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DmpLifecycle(DataLifecycle.PUBLISHED)), XSD.xboolean, MetricType.CHECKING)
        val DOWNLOAD_URL_AVAILABLE_METRIC = Metric("Avilability of a download url", "DownloadUrlAvailable", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DmpLifecycle(DataLifecycle.PUBLISHED)), XSD.xboolean, MetricType.CHECKING)
        val HOST_URL_AVAILABLE_METRIC = Metric("Avilability of a host url", "HostUrlAvailable", CategoryDimmensionModels.AVAILABILITY_DIMENSION, listOf(DmpLifecycle(DataLifecycle.PUBLISHED)), XSD.xboolean, MetricType.CHECKING)
    }
}