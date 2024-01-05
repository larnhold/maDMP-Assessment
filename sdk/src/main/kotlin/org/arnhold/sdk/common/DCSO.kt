package org.arnhold.sdk.common

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DCSO {
    companion object {
        val uri = "https://w3id.org/dcso/ns/core#"
        val terms = "http://purl.org/dc/terms/"

        private val m = ModelFactory.createDefaultModel()


        val CREATED: Property = m.createProperty(String.format("%screated", uri))
        val DESCRIPTION: Property = m.createProperty(String.format("%sdescription", terms))
        val ETHICAL_ISSUES_DESCRIPTION: Property = m.createProperty(String.format("%sethicalIssueDescription", uri))
        val ETHICAL_ISSUES_EXIST: Property = m.createProperty(String.format("%sethicalIssuesExist", uri))
        val ETHICAL_ISSUES_REPORT: Property = m.createProperty(String.format("%sethicalIssuesExist", uri))
        val LANGUAGE: Property = m.createProperty(String.format("%slanguage", uri))
        val MODIFIED: Property = m.createProperty(String.format("%smodified", uri))
        val TITLE: Property = m.createProperty(String.format("%stitle", terms))

        val IDENTIFIER: Property = m.createProperty(String.format("%sidentifier", uri))
        val TYPE: Property = m.createProperty(String.format("%sidentifierType", uri))

        val HAS_DMP_ID: Property = m.createProperty(String.format("%shasDMPId", uri))
    }
}