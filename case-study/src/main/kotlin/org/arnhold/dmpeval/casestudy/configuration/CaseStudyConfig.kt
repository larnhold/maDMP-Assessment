package org.arnhold.dmpeval.casestudy.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "case-study")
data class CaseStudyConfig(
    var test: String = ""
)