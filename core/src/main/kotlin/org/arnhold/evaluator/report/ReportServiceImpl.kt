package org.arnhold.evaluator.report

import org.arnhold.evaluator.metricAggregation.MetricAggregationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ReportServiceImpl @Autowired constructor(
        val metricAggregationService: MetricAggregationService
) : ReportService {
}