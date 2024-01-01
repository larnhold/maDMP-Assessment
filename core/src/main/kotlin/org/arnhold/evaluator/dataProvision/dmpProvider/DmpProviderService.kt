package org.arnhold.evaluator.dataProvision.dmpProvider

interface DmpProviderService {

    fun getProviders(): List<DmpProviderConfiguration>
    fun getProviderConfiguration(identifier: String): DmpProviderConfiguration

}