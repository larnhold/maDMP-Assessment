package org.arnhold.dmpeval.casestudy

import okhttp3.OkHttpClient
import org.arnhold.dmpeval.casestudy.configuration.CaseStudyConfig
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@SpringBootConfiguration
@Configuration
@ComponentScan(basePackages = ["at.ac.tuwien", "org.arnhold"])
@EnableConfigurationProperties(CaseStudyConfig::class)
class Configuration {
    @Bean
    fun caseStudyConfig(): CaseStudyConfig {
        return CaseStudyConfig()
    }

    @Bean
    fun okHttpCLient(): OkHttpClient {
        return OkHttpClient().newBuilder().followRedirects(true).build()
    }
}