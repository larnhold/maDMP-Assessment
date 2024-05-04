package org.arnhold.evaluator.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(OntologyConfig::class, ExtensionConfig::class)
class AppConfig {
    @Bean
    fun ontologyConfig(): OntologyConfig {
        return OntologyConfig()
    }

    @Bean
    fun extensionConfig(): ExtensionConfig {
        return ExtensionConfig()
    }

    @Bean
    fun objectWriter(): ObjectWriter {
        return ObjectMapper().writerWithDefaultPrettyPrinter().withDefaultPrettyPrinter()
    }
}