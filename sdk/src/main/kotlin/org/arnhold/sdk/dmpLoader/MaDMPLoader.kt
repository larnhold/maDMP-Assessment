package org.arnhold.sdk.dmpLoader

import org.apache.jena.rdf.model.Model
import java.io.File

interface MaDMPLoader {
    fun fromIdentifier(identifier: File): Model
}
