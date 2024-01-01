package org.arnhold.evaluator.dataProvision.dmpProvider

import org.springframework.stereotype.Component

@Component
class DmpProviderServiceImpl: DmpProviderService {

    override fun getProviders(): List<DmpProviderConfiguration> {
        TODO("Not yet implemented")
    }

    override fun getProviderConfiguration(identifier: String): DmpProviderConfiguration {
        TODO("Not yet implemented")
    }
}