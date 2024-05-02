package org.arnhold.dmpeval.casestudy

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import okhttp3.OkHttpClient
import org.apache.commons.validator.routines.UrlValidator
import org.arnhold.sdk.tools.XMLParser
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

    @Bean
    fun xmlParser(): XMLParser {
        return XMLParser()
    }

    @Bean
    fun objectWriter(): ObjectWriter {
        return ObjectMapper().writerWithDefaultPrettyPrinter().withDefaultPrettyPrinter()
    }
}