package org.arnhold.sdk.dmpLoader

import org.apache.jena.rdf.model.Model
import org.springframework.plugin.core.Plugin
import java.io.File

interface DmpLoaderPlugin: Plugin<String> {
    fun fromIdentifier(identifier: File): Model
}