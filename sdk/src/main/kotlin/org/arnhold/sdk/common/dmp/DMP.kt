package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.DCSO

data class DMP (
    @JsonProperty("contact")
    val contact: Contact?,
    @JsonProperty("contributor")
    val contributors: List<Contributor>?,
    @JsonProperty("cost")
    val costs: List<Cost>?,
    @JsonProperty("project")
    val projects: List<Project>?,
    @JsonProperty("dataset")
    val datasets: List<Dataset>?,
    @JsonProperty("created")
    val created: String?,
    @JsonProperty("dmp_id")
    val dmpId: DmpId?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("ethical_issues_description")
    val ethicalIssuesDescription: String?,
    @JsonProperty("ethical_issues_exist")
    val ethicalIssuesExist: String?,
    @JsonProperty("ethical_issues_report")
    val ethicalIssuesReport: String?,
    @JsonProperty("language")
    val language: String?,
    @JsonProperty("modified")
    val modified: String?,
    @JsonProperty("title")
    val title: String?
) : RdfResourceProvider {

    companion object {
        const val PREFIX = "https://w3id.org/dcso/ns/core#"
    }
    override fun toResource(model: Model, name: String): Resource {
        val dmpResource = model.createResource(String.format("%s%s", PREFIX, name))

        // data properties
        addDataProperty(dmpResource, DCSO.CREATED, created)
        addDataProperty(dmpResource, DCSO.DESCRIPTION, description)
        addDataProperty(dmpResource, DCSO.ETHICAL_ISSUES_DESCRIPTION, ethicalIssuesDescription)
        addDataProperty(dmpResource, DCSO.ETHICAL_ISSUES_EXIST, ethicalIssuesExist)
        addDataProperty(dmpResource, DCSO.ETHICAL_ISSUES_REPORT, ethicalIssuesReport)
        addDataProperty(dmpResource, DCSO.LANGUAGE, language)
        addDataProperty(dmpResource, DCSO.MODIFIED, modified)
        addDataProperty(dmpResource, DCSO.TITLE, "test")

        // object properties
        addObjectProperty(model, dmpResource, DCSO.HAS_DMP_ID, dmpId, "dmpID_0")
        /*
        dmpId
        contact
        contributors
        costs
        projects
        datasets
        */

        return dmpResource
    }

    private fun addDataProperty(subj: Resource, verb: Property, obj: String?) {
        obj?.let {
            subj.addProperty(verb, it)
        }
    }

    private fun addObjectProperty(model: Model, subj: Resource, verb: Property, obj: RdfResourceProvider?, name: String) {
        obj?.let {
            subj.addProperty(verb, obj.toResource(model, name))
        }
    }
}