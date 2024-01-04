package org.arnhold.sdk.common.dmp

import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.jena.rdf.model.Resource

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
    override fun toResource(resource: Resource): Resource {
        TODO("Not yet implemented")
    }
}