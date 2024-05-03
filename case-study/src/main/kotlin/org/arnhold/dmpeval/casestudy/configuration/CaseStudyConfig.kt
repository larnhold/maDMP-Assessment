package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CaseStudyConfig {
    @Bean
    fun getDataStoreConfig(): DataStoreConfig {
        return DataStoreConfig()
    }
}