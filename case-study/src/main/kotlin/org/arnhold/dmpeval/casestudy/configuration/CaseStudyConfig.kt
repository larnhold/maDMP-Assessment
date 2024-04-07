package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TripleStoreConfig::class)
class CaseStudyConfig {
    @Bean
    fun tripleStoreConfig(): TripleStoreConfig {
        return TripleStoreConfig()
    }
}