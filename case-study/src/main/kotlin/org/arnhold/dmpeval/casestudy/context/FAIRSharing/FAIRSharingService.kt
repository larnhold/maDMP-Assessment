package org.arnhold.dmpeval.casestudy.context.FAIRSharing

interface FAIRSharingService {
    fun isStandard(value: String): Boolean
    fun isDatabase(value: String): Boolean
}