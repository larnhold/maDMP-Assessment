package org.arnhold.sdk.vocab.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DCSO {
    companion object {
        const val URI_PREFIX = "https://w3id.org/dcso/ns/core#"
        const val TERMS_PREFIX = "http://purl.org/dc/terms/"
        const val FOAF_PREFIX = "http://xmlns.com/foaf/0.1/"

        private val m = ModelFactory.createDefaultModel()


        val CREATED: Property = m.createProperty(String.format("%screated", URI_PREFIX))
        val DESCRIPTION: Property = m.createProperty(String.format("%sdescription", TERMS_PREFIX))
        val ETHICAL_ISSUES_DESCRIPTION: Property = m.createProperty(String.format("%sethicalIssueDescription", URI_PREFIX))
        val ETHICAL_ISSUES_EXIST: Property = m.createProperty(String.format("%sethicalIssuesExist", URI_PREFIX))
        val ETHICAL_ISSUES_REPORT: Property = m.createProperty(String.format("%sethicalIssuesReport", URI_PREFIX))
        val LANGUAGE: Property = m.createProperty(String.format("%slanguage", URI_PREFIX))
        val MODIFIED: Property = m.createProperty(String.format("%smodified", URI_PREFIX))
        val TITLE: Property = m.createProperty(String.format("%stitle", TERMS_PREFIX))
        val IDENTIFIER: Property = m.createProperty(String.format("%sidentifier", URI_PREFIX))
        val TYPE: Property = m.createProperty(String.format("%sidentifierType", URI_PREFIX))
        val MBOX = m.createProperty(String.format("%smbox", FOAF_PREFIX))
        val NAME = m.createProperty(String.format("%sname", FOAF_PREFIX))
        val HAS_DMP_ID: Property = m.createProperty(String.format("%shasDMPId", URI_PREFIX))
        val URL: Property = m.createProperty(String.format("%surl", URI_PREFIX))
        val VALUE: Property = m.createProperty(String.format("%svalue", URI_PREFIX))
        val SUPPORT_VERSIONING: Property = m.createProperty(String.format("%ssupportVersioning", URI_PREFIX))
        val STORAGE_TYPE: Property = m.createProperty(String.format("%sstorageType", URI_PREFIX))
        val START_DATE: Property = m.createProperty(String.format("%sstartDate", URI_PREFIX))
        val START: Property = m.createProperty(String.format("%sstart", URI_PREFIX))
        val END: Property = m.createProperty(String.format("%send", URI_PREFIX))
        val SENSITIVE_DATA: Property = m.createProperty(String.format("%ssensitiveData", URI_PREFIX))
        val ROLE: Property = m.createProperty(String.format("%srole", URI_PREFIX))
        val PRESERVATION_STATEMENT: Property = m.createProperty(String.format("%spreservationStatement", URI_PREFIX))
        val PID_SYSTEM: Property = m.createProperty(String.format("%spidSystem", URI_PREFIX))
        val PERSONAL_DATA: Property = m.createProperty(String.format("%spersonalData", URI_PREFIX))
        val LICENSE_REF: Property = m.createProperty(String.format("%slicenseRef", URI_PREFIX))
        val GEOLOCATION: Property = m.createProperty(String.format("%sgeoLocation", URI_PREFIX))
        val FUNDING_STATUS: Property = m.createProperty(String.format("%sfundingStatus", URI_PREFIX))
        val DATASET_TYPE: Property = m.createProperty(String.format("%sdatasetType", URI_PREFIX))
        val DATA_QUALITY_ASSURANCE: Property = m.createProperty(String.format("%sdataQualityAssurance", URI_PREFIX))
        val DATA_ACCESS: Property = m.createProperty(String.format("%sdataAccess", URI_PREFIX))
        val CURRENCY_CODE: Property = m.createProperty(String.format("%scurencyCode", URI_PREFIX))
        val CERTIFIED_WITH: Property = m.createProperty(String.format("%scertifiedWith", URI_PREFIX))
        val BACKUP_TYPE: Property = m.createProperty(String.format("%sbackupType", URI_PREFIX))
        val BACKUP_FREQUENCY: Property = m.createProperty(String.format("%sbackupFrequency", URI_PREFIX))
        val AVAILABLE_UNTIL: Property = m.createProperty(String.format("%savailableUntil", URI_PREFIX))
        val AVAILABILITY: Property = m.createProperty(String.format("%savailability", URI_PREFIX))
        val KEYWORD: Property = m.createProperty(String.format("%skeyword", URI_PREFIX))
        val DOWNLOAD_URL: Property = m.createProperty(String.format("%sdownloadURL", URI_PREFIX))
        val BYTE_SIZE: Property = m.createProperty(String.format("%sbyteSize", URI_PREFIX))
        val ACCESS_URL: Property = m.createProperty(String.format("%saccessURL", URI_PREFIX))
        val ISSUED: Property = m.createProperty(String.format("%sissued", URI_PREFIX))
        val FORMAT: Property = m.createProperty(String.format("%sformat", TERMS_PREFIX))
        val HAS_CONTACT: Property = m.createProperty(String.format("%shasContact", URI_PREFIX))
        val HAS_CONTACT_ID: Property = m.createProperty(String.format("%shasContactId", URI_PREFIX))
        val HAS_CONTRIBUTOR: Property = m.createProperty(String.format("%shasContributor", URI_PREFIX))
        val HAS_CONTRIBUTOR_ID: Property = m.createProperty(String.format("%shasContributorId", URI_PREFIX))
        val HAS_COST: Property = m.createProperty(String.format("%shasCost", URI_PREFIX))
        val HAS_DATASET: Property = m.createProperty(String.format("%shasDataset", URI_PREFIX))
        val HAS_DATASET_ID: Property = m.createProperty(String.format("%shasDatasetId", URI_PREFIX))
        val HAS_FUNDER_ID: Property = m.createProperty(String.format("%shasFunderId", URI_PREFIX))
        val HAS_DISTRIBUTION: Property = m.createProperty(String.format("%shasDistribution", URI_PREFIX))
        val HAS_FUNDING: Property = m.createProperty(String.format("%shasFunding", URI_PREFIX))
        val HAS_GRANT_ID: Property = m.createProperty(String.format("%shasGrantId", URI_PREFIX))
        val HAS_HOST: Property = m.createProperty(String.format("%shasHost", URI_PREFIX))
        val HAS_ID: Property = m.createProperty(String.format("%shasId", URI_PREFIX))
        val HAS_LICENSE: Property = m.createProperty(String.format("%shasLicense", URI_PREFIX))
        val HAS_METADATA: Property = m.createProperty(String.format("%shasMetadata", URI_PREFIX))
        val HAS_METADATA_STANDARD_ID: Property = m.createProperty(String.format("%shasMetadataStandardId", URI_PREFIX))
        val HAS_PROJECT: Property = m.createProperty(String.format("%shasProject", URI_PREFIX))
        val HAS_SECURITY_AND_PRIVACY: Property = m.createProperty(String.format("%shasSecurityPrivacy", URI_PREFIX))
        val HAS_TECHNICAL_RESOURCE: Property = m.createProperty(String.format("%shasTechnicalResource", URI_PREFIX))
    }
}