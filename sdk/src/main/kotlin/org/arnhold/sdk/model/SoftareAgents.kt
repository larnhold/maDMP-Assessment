package org.arnhold.sdk.model

import org.arnhold.sdk.vocab.dqv.SoftwareAgent

class SoftareAgents {
    companion object {
        val DMPEVAL = SoftwareAgent("maDMPEvaluator", "")
        val FUJI = SoftwareAgent("FUJI", "F-UJI is a web service to programatically assess FAIRness of research data objects at the dataset level based on the FAIRsFAIR Data Object Assessment Metrics")
        val SHACL_AGENT = SoftwareAgent("Apache Jena SHACL Validator", null)
    }
}
