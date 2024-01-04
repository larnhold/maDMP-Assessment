package org.arnhold.evaluator.methodProvision

import org.arnhold.evaluator.plugin.PluginLoader
import org.arnhold.sdk.common.dqv.Metric
import org.arnhold.sdk.evaluator.EvaluationMethodPlugin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MethodProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : MethodProviderService {

    override fun getSuitableEvaluatorForMetric(metric: Metric): EvaluationMethodPlugin? {
        return pluginLoader.getEvaluators().find { it.suitableForMetric(metric) }
    }
}