package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import org.apache.jena.vocabulary.XSD
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiMetricTest
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiResult
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.dqv.*


class QualityOfActionsMetricModels {
    companion object {

        val DATASET_ACHIEVED_FAIRNESS_METRIC = Metric(
            "dataset_achieved_fairness_metric",
            "Indicate the achieved value of a FAIR metric of a published dataset object contained in the DMP as reported by an automatic FAIR evaluator",
            "Dataset Achieved FAIRness",
            CategoryDimmensionModels.FAIR_DIMENSION,
            listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
            XSD.integer.toString()
        )

        val FUJI_METRIC_GROUP = MetricGroup(
            "fuji_metric_group",
            "FUJI Metrics",
            ""
        )

        fun metricFromFujiResult(result: FujiResult): Metric {


            return Metric(
                result.metricIdentifier,
                result.metricName,
                result.metricIdentifier,
                fujiResultToDimension(result),
                listOf(DmpLifecycle(DataLifecycle.PUBLISHED)),
                XSD.integer.toString(),
                result.metricTests.map { (key, value) -> metricTestFromFujiMetricTest(key, value) }.toMutableList(),
                result.score.total.toString(),
                FUJI_METRIC_GROUP
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