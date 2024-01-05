package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.DCSO
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider

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
) : RdfResourceProvider() {
    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, name, listOf(
            DataPropertyDefinition(DCSO.CREATED, created),
            DataPropertyDefinition(DCSO.DESCRIPTION, description),
            DataPropertyDefinition(DCSO.ETHICAL_ISSUES_DESCRIPTION, ethicalIssuesDescription),
            DataPropertyDefinition(DCSO.ETHICAL_ISSUES_EXIST, ethicalIssuesExist),
            DataPropertyDefinition(DCSO.ETHICAL_ISSUES_REPORT, ethicalIssuesReport),
            DataPropertyDefinition(DCSO.LANGUAGE, language),
            DataPropertyDefinition(DCSO.MODIFIED, modified),
            DataPropertyDefinition(DCSO.TITLE, title),
        ), listOf(
            ObjectPropertyDefinition(DCSO.HAS_DMP_ID, dmpId,  name, "dmpID"),
            ObjectPropertyDefinition(DCSO.HAS_CONTACT, contact, name, "contact"),
            ObjectPropertyDefinition(DCSO.HAS_CONTRIBUTOR, contributors, name, "contributor"),
            ObjectPropertyDefinition(DCSO.HAS_CONTRIBUTOR, costs, name, "cost"),
            ObjectPropertyDefinition(DCSO.HAS_CONTRIBUTOR, projects, name, "project"),
            ObjectPropertyDefinition(DCSO.HAS_CONTRIBUTOR, datasets, name, "dataset")
        ))
    }
}