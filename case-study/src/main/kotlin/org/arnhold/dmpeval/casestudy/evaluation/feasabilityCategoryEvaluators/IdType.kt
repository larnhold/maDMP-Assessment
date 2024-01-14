package org.arnhold.dmpeval.casestudy.evaluation.feasabilityCategoryEvaluators

import java.util.*

enum class IdType(type: String) {
    UNDEFINED(""),
    DOI("DOI"),
    ORCID("ORCID");

    companion object {
        fun from(value: String?): IdType {
            return if (value == null) {
                UNDEFINED
            } else {
                try {
                    entries.first { it.toString() == value.uppercase(Locale.getDefault()) }
                } catch (e: NoSuchElementException) {
                    UNDEFINED
                }
            }
        }
    }
}