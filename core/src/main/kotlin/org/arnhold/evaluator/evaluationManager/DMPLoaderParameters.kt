package org.arnhold.evaluator.evaluationManager

data class DMPLoaderParameters (
        val dmpLoader: String = "madmpfileloader",
        val dmpIdentifier: String = "zenodo/11",
        val properties: Map<String, String>
) {
}