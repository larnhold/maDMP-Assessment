package org.arnhold.sdk.tools.sparqlSelector

import mu.KotlinLogging
import org.apache.jena.query.*
import org.springframework.stereotype.Component
import org.apache.jena.rdf.model.Model
import org.arnhold.sdk.vocab.ontologyDefinitions.DCSO

@Component
class SparqlSelector {

    private val logger = KotlinLogging.logger {}

    fun getSelectResults(dmp: Model, queryString: String): List<SparqlSelectorResult> {
        val queryWithPrefixes: Query = QueryFactory.create()
        dmp.nsPrefixMap.forEach { (key, value) -> queryWithPrefixes.setPrefix(key, value) }

        val query = QueryFactory.parse(
            queryWithPrefixes,
            queryString,
            DCSO.URI_PREFIX,
            Syntax.defaultQuerySyntax
        )

        logger.info { "Run selector query \n\n $queryString" }
        val qexec = QueryExecutionFactory.create(query, dmp)
        val results = qexec.execSelect()
        val mapped = results.asSequence().map { mapEntry(it) }.toList()
        logger.info { "Selector returned ${mapped.size} entries" }
        qexec.close()
        return mapped
    }

    private fun mapEntry(solution: QuerySolution): SparqlSelectorResult {
        val literal = solution.varNames().asSequence().filter { solution.get(it).isLiteral }.associateBy ( {it}, { solution.getLiteral(it) })
        val resources = solution.varNames().asSequence().filter { solution.get(it).isResource }.associateBy ( {it}, { solution.getResource(it) })
        return SparqlSelectorResult(literal, resources)
    }
}