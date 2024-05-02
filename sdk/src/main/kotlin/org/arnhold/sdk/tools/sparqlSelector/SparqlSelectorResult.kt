package org.arnhold.sdk.tools.sparqlSelector

import org.apache.jena.rdf.model.Literal
import org.apache.jena.rdf.model.Resource

data class SparqlSelectorResult(
    val literals: Map<String, Literal>,
    val resources: Map<String, Resource>
)
