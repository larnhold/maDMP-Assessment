package org.arnhold.evaluator.evaluation

data class DMPLoaderParameters (
        val dmpLoader: String = "madmpfileloader",
        val dmpIdentifier: String = "zenodo/11",
        val properties: Map<String, String>
) {
}