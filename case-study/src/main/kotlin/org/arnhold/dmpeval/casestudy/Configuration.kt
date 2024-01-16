package org.arnhold.dmpeval.casestudy

import okhttp3.OkHttpClient
import org.apache.commons.validator.routines.UrlValidator
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan


@SpringBootConfiguration
@ComponentScan(basePackages = ["at.ac.tuwien", "org.arnhold"])
class Configuration {

    @Bean
    fun urlValidator(): UrlValidator {
        return UrlValidator()
    }

    @Bean
    fun okHttpCLient(): OkHttpClient {
        return OkHttpClient().newBuilder().followRedirects(true).build()
    }
}