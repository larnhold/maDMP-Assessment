package org.arnhold.dmpeval.casestudy.context.openAire

import org.arnhold.sdk.context.schema.Dataset

interface OpenAireService {
    fun findDatasetByDoi(doi: String): Dataset?
}
