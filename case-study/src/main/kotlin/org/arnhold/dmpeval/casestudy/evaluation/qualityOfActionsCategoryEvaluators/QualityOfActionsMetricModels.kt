package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import org.apache.jena.rdf.model.Resource
import org.apache.jena.vocabulary.XSD
import org.arnhold.sdk.model.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiMetricTest
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiResult
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.Dimension
import org.arnhold.sdk.vocab.dqv.DmpLifecycle
import org.arnhold.sdk.vocab.dqv.Metric
import org.arnhold.sdk.vocab.dqv.MetricTestDefinition


class QualityOfActionsMetricModels {
    companion object {

        fun metricFromFujiResult(datasetIdType: Resource, result: FujiResult): Metric {
            return Metric(
                "fuji-result-metric",
                result.metricName,
                result.metricIdentifier,
                fujiResultToDimension(result),
                listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
                XSD.integer.toString(),
                result.metricTests.map { (key, value) -> metricTestFromFujiMetricTest(key, value) }.toMutableList(),
                result.score.total.toString()
            )
        }

        private fun fujiResultToDimension(result: FujiResult): Dimension {
            return when(result.metricIdentifier) {
                "FsF-F1-01D" -> CategoryDimmensionModels.FINDABLE_DIMENSION
                "FsF-F1-02D" -> CategoryDimmensionModels.FINDABLE_DIMENSION
                "FsF-F2-01M" -> CategoryDimmensionModels.FINDABLE_DIMENSION
                "FsF-F3-01M" -> CategoryDimmensionModels.FINDABLE_DIMENSION
                "FsF-F4-01M" -> CategoryDimmensionModels.FINDABLE_DIMENSION
                "FsF-I1-01M" -> CategoryDimmensionModels.INTEROPERABLE_DIMENSION
                "FsF-I2-01M" -> CategoryDimmensionModels.INTEROPERABLE_DIMENSION
                "FsF-I3-01M" -> CategoryDimmensionModels.INTEROPERABLE_DIMENSION
                "FsF-R1-01MD" -> CategoryDimmensionModels.REUSABLE_DIMENSION
                "FsF-R1.1-01M" -> CategoryDimmensionModels.REUSABLE_DIMENSION
                "FsF-A1-01M" -> CategoryDimmensionModels.ACCESSIBLE_DIMENSION
                "FsF-R1.2-01M" -> CategoryDimmensionModels.REUSABLE_DIMENSION
                "FsF-R1.3-01M" -> CategoryDimmensionModels.REUSABLE_DIMENSION
                "FsF-R1.3-02D" -> CategoryDimmensionModels.REUSABLE_DIMENSION
                "FsF-A1-03D" -> CategoryDimmensionModels.ACCESSIBLE_DIMENSION
                "FsF-A1-02M" -> CategoryDimmensionModels.ACCESSIBLE_DIMENSION
                else -> {
                    CategoryDimmensionModels.FAIR_DIMENSION
                }
            }
        }

        private fun metricTestFromFujiMetricTest(title: String, test: FujiMetricTest): MetricTestDefinition {
            return MetricTestDefinition(
                "fuji-test-result",
                title,
                test.name,
                test.metricTestScore.total.toString(),
                XSD.integer.toString()
            )
        }
    }
}