package org.arnhold.dmpeval.casestudy

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootConfiguration
@Configuration
@ComponentScan(basePackages = ["at.ac.tuwien", "org.arnhold"])
class Configuration