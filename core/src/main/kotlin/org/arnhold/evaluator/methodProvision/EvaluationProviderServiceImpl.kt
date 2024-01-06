package org.arnhold.evaluator.methodProvision

import org.arnhold.evaluator.plugin.PluginLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EvaluationProviderServiceImpl @Autowired constructor(
    var pluginLoader: PluginLoader
) : EvaluationProviderService