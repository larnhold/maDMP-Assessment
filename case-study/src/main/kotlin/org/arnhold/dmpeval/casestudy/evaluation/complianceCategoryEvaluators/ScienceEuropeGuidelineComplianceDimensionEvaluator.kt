package org.arnhold.dmpeval.casestudy.evaluation.complianceCategoryEvaluators

import org.apache.jena.ontology.OntModel
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.configuration.QueriesConfig
import org.arnhold.dmpeval.casestudy.context.FAIRSharing.FAIRSharingService
import org.arnhold.dmpeval.casestudy.evaluation.CategoryDimmensionModels
import org.arnhold.sdk.model.EvaluationDimensionConstants
import org.arnhold.sdk.evaluator.DimensionEvaluatorPlugin
import org.arnhold.sdk.evaluator.EvaluatorInformation
import org.arnhold.sdk.model.EvaluationTaskParameters
import org.arnhold.sdk.model.SoftareAgents
import org.arnhold.sdk.tools.sparqlSelector.SparqlSelector
import org.arnhold.sdk.vocab.constants.DataLifecycle
import org.arnhold.sdk.vocab.constants.Extension
import org.arnhold.sdk.vocab.context.DMPContext
import org.arnhold.sdk.vocab.dqv.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class ScienceEuropeGuidelineComplianceDimensionEvaluator @Autowired constructor(
    val queriesConfig: QueriesConfig,
    val sparqlSelector: SparqlSelector,
    val fairSharingService: FAIRSharingService
) : DimensionEvaluatorPlugin {

    override fun getPluginIdentifier(): String {
        return EvaluationDimensionConstants.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE.toString()
    }

    override fun getPluginInformation(): EvaluatorInformation {
        return EvaluatorInformation(
            CategoryDimmensionModels.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE_DIMENSION,
            CategoryDimmensionModels.COMPLIANCE_CATEGORY,
            listOf()
        )
    }

    override fun getAllMeasurements(
        dmp: Model,
        context: List<DMPContext>,
        parameters: EvaluationTaskParameters,
        dmpOntology: OntModel,
        extensionOntologies: Map<Extension, OntModel>
    ): List<Measurement> {
        return if (extensionOntologies.containsKey(Extension.SCIENCE_EUROPE)) {
            val query = Path.of( queriesConfig.directory + "/allHosts.sparql").toFile().readText(Charsets.UTF_8)
            val selected = sparqlSelector.getSelectResults(dmp, query)

            return selected.mapNotNull {
                val title = it.literals.get("title").toString()
                val host = it.resources.get("host").toString()
                return@mapNotNull hostInFAIRSharing(host, title)
            }
        } else {
            listOf()
        }
    }

    private fun hostInFAIRSharing(host: String, title: String): Measurement {
        val category: String = "How will data and metadata be stored and backed up during the research?"
        val guideline: String = "Give preference to the use of robust, managed storage with automatic" +
                "backup, such as provided by IT support services of the home institution."

        val categoryDimension = Dimension(
            null,
            category,
            "",
            CategoryDimmensionModels.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE_DIMENSION
        )

        val guidelineDimension = Dimension(
            null,
            guideline,
            "",
            categoryDimension
        )

        val metric = ScienceEuropeComplianceMetricModels.SCIENCE_EUROPE_GUIDELINE_VERIFICATION_METRIC.copy()
        metric.inDimension = guidelineDimension

        val value = fairSharingService.isDatabase(title)

        return Measurement(
            DmpLifecycle(DataLifecycle.PUBLISHED),
            metric,
            null,
            DMPLocation(host, null),
            value.toString(),
            softwareAgent= SoftareAgents.DMPEVAL,
        )
    }

    override fun supports(p0: String): Boolean {
        return EvaluationDimensionConstants.SCIENCE_EUROPE_GUIDELINE_COMPLIANCE.toString() == p0
    }
}