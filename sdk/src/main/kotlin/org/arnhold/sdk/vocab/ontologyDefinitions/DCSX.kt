package org.arnhold.sdk.vocab.ontologyDefinitions

import org.apache.jena.rdf.model.ModelFactory
import org.apache.jena.rdf.model.Property

class DCSX {
    companion object {
        const val URI_PREFIX = "https://w3id.org/dcso/ns/extension#"

        private val m = ModelFactory.createDefaultModel()

        val HAS_DATA_RECOVERY_EXPLANATION_ID: Property = m.createProperty(String.format("%shasDataRecoveryExplanationId", URI_PREFIX))
        val HAS_PROPERTY_RIGHTS_EXPLANATION_ID: Property = m.createProperty(String.format("%shasPropertyRightsExplanationID", URI_PREFIX))
        val HAS_DATA_QUALIY_ASSURANCE_RESOURCE_ID: Property = m.createProperty(String.format("%shasDataQualityAssuranceRessourceId", URI_PREFIX))
        val HAS_METHODOLOGY_ID: Property = m.createProperty(String.format("%shasMethodologyId", URI_PREFIX))
        val HAS_RELATED_POLICY_ID: Property = m.createProperty(String.format("%shasRelatedPolicyId", URI_PREFIX))

        val HAS_DATA_RECOVERY_EXPLANATION: Property = m.createProperty(String.format("%shasDataRecoveryExplanation", URI_PREFIX))
        val HAS_PROPERTY_RIGHTS_EXPLANATION: Property = m.createProperty(String.format("%shasPropertyRightsExplanation", URI_PREFIX))
        val HAS_DATA_QUALIY_ASSURANCE_RESOURCE: Property = m.createProperty(String.format("%shasDataQualityAssuranceRessource", URI_PREFIX))
        val HAS_METHODOLOGY: Property = m.createProperty(String.format("%shasMethodology", URI_PREFIX))
        val HAS_RELATED_POLICY: Property = m.createProperty(String.format("%shasRelatedPolicy", URI_PREFIX))

        val IS_REUSED: Property = m.createProperty(String.format("%sisReused", URI_PREFIX))
        val RESTRICTION_EXPLANATION: Property = m.createProperty(String.format("%srestrictionExplanation", URI_PREFIX))
        val TARGET_AUDIENCE: Property = m.createProperty(String.format("%stragetAudience", URI_PREFIX))
    }
}