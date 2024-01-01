package org.arnhold.evaluator.dataProvision.dmpProvider

data class DmpProviderConfiguration(
        val identifier: String,
        val requiredProperties: Map<String, String>
)
