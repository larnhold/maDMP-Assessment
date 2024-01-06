package org.arnhold.sdk.common.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DCSO {
    companion object {
        const val uri = "https://w3id.org/dcso/ns/core#"
        const val terms = "http://purl.org/dc/terms/"
        const val foaf = "http://xmlns.com/foaf/0.1/"

        private val m = ModelFactory.createDefaultModel()


        val CREATED: Property = m.createProperty(String.format("%screated", uri))
        val DESCRIPTION: Property = m.createProperty(String.format("%sdescription", terms))
        val ETHICAL_ISSUES_DESCRIPTION: Property = m.createProperty(String.format("%sethicalIssueDescription", uri))
        val ETHICAL_ISSUES_EXIST: Property = m.createProperty(String.format("%sethicalIssuesExist", uri))
        val ETHICAL_ISSUES_REPORT: Property = m.createProperty(String.format("%sethicalIssuesReport", uri))
        val LANGUAGE: Property = m.createProperty(String.format("%slanguage", uri))
        val MODIFIED: Property = m.createProperty(String.format("%smodified", uri))
        val TITLE: Property = m.createProperty(String.format("%stitle", terms))
        val IDENTIFIER: Property = m.createProperty(String.format("%sidentifier", uri))
        val TYPE: Property = m.createProperty(String.format("%sidentifierType", uri))
        val MBOX = m.createProperty(String.format("%smbox", foaf))
        val NAME = m.createProperty(String.format("%sname", foaf))
        val HAS_DMP_ID: Property = m.createProperty(String.format("%shasDMPId", uri))
        val URL: Property = m.createProperty(String.format("%surl", uri))
        val VALUE: Property = m.createProperty(String.format("%svalue", uri))
        val SUPPORT_VERSIONING: Property = m.createProperty(String.format("%ssupportVersioning", uri))
        val STORAGE_TYPE: Property = m.createProperty(String.format("%sstorageType", uri))
        val START_DATE: Property = m.createProperty(String.format("%sstartDate", uri))
        val START: Property = m.createProperty(String.format("%sstart", uri))
        val END: Property = m.createProperty(String.format("%send", uri))
        val SENSITIVE_DATA: Property = m.createProperty(String.format("%ssensitiveData", uri))
        val ROLE: Property = m.createProperty(String.format("%srole", uri))
        val PRESERVATION_STATEMENT: Property = m.createProperty(String.format("%spreservationStatement", uri))
        val PID_SYSTEM: Property = m.createProperty(String.format("%spidSystem", uri))
        val PERSONAL_DATA: Property = m.createProperty(String.format("%spersonalData", uri))
        val LICENSE_REF: Property = m.createProperty(String.format("%slicenseRef", uri))
        val GEOLOCATION: Property = m.createProperty(String.format("%sgeoLocation", uri))
        val FUNDING_STATUS: Property = m.createProperty(String.format("%sfundingStatus", uri))
        val DATASET_TYPE: Property = m.createProperty(String.format("%sdatasetType", uri))
        val DATA_QUALITY_ASSURANCE: Property = m.createProperty(String.format("%sdataQualityAssurance", uri))
        val DATA_ACCESS: Property = m.createProperty(String.format("%sdataAccess", uri))
        val CURRENCY_CODE: Property = m.createProperty(String.format("%scurencyCode", uri))
        val CERTIFIED_WITH: Property = m.createProperty(String.format("%scertifiedWith", uri))
        val BACKUP_TYPE: Property = m.createProperty(String.format("%sbackupType", uri))
        val BACKUP_FREQUENCY: Property = m.createProperty(String.format("%sbackupFrequency", uri))
        val AVAILABLE_UNTIL: Property = m.createProperty(String.format("%savailableUntil", uri))
        val AVAILABILITY: Property = m.createProperty(String.format("%savailability", uri))
        val KEYWORD: Property = m.createProperty(String.format("%skeyword", uri))
        val DOWNLOAD_URL: Property = m.createProperty(String.format("%sdownloadURL", uri))
        val BYTE_SIZE: Property = m.createProperty(String.format("%sbyteSize", uri))
        val ACCESS_URL: Property = m.createProperty(String.format("%saccessURL", uri))
        val ISSUED: Property = m.createProperty(String.format("%sissued", uri))
        val FORMAT: Property = m.createProperty(String.format("%sformat", terms))
        val HAS_CONTACT: Property = m.createProperty(String.format("%shasContact", uri))
        val HAS_CONTACT_ID: Property = m.createProperty(String.format("%shasContactId", uri))
        val HAS_CONTRIBUTOR: Property = m.createProperty(String.format("%shasContributor", uri))
        val HAS_CONTRIBUTOR_ID: Property = m.createProperty(String.format("%shasContributorId", uri))
        val HAS_COST: Property = m.createProperty(String.format("%shasCost", uri))
        val HAS_DATASET: Property = m.createProperty(String.format("%shasDataset", uri))
        val HAS_DATASET_ID: Property = m.createProperty(String.format("%shasDatasetId", uri))
        val HAS_FUNDER_ID: Property = m.createProperty(String.format("%shasFunderId", uri))
        val HAS_DISTRIBUTION: Property = m.createProperty(String.format("%shasDistribution", uri))
        val HAS_FUNDING: Property = m.createProperty(String.format("%shasFunding", uri))
        val HAS_GRANT_ID: Property = m.createProperty(String.format("%shasGrantId", uri))
        val HAS_HOST: Property = m.createProperty(String.format("%shasHost", uri))
        val HAS_ID: Property = m.createProperty(String.format("%shasId", uri))
        val HAS_LICENSE: Property = m.createProperty(String.format("%shasLicense", uri))
        val HAS_METADATA: Property = m.createProperty(String.format("%shasMetadata", uri))
        val HAS_METADATA_STANDARD_ID: Property = m.createProperty(String.format("%shasMetadataStandardId", uri))
        val HAS_PROJECT: Property = m.createProperty(String.format("%shasProject", uri))
        val HAS_SECURITY_AND_PRIVACY: Property = m.createProperty(String.format("%shasSecurityPrivacy", uri))
        val HAS_TECHNICAL_RESOURCE: Property = m.createProperty(String.format("%shasTechnicalResource", uri))
    }
}