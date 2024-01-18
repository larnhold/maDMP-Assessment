package org.arnhold.sdk.common.dqv

import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Resource
import org.arnhold.sdk.common.dmp.helper.DataPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.ObjectPropertyDefinition
import org.arnhold.sdk.common.dmp.helper.RdfResourceProvider
import org.arnhold.sdk.common.ontologyDefinitions.DMPDQV

data class Dimension(
    val inCategory: Category?,
    val title: String?,
    val description: String?,
    var derivedFrom: Dimension? = null
): RdfResourceProvider() {

    override fun toResource(model: Model, name: String): Resource {
        return super.toResource(model, title + "_Dimension", listOf(
            DataPropertyDefinition(DMPDQV.DESCRIPTION, description),
            DataPropertyDefinition(DMPDQV.TITLE, title)
        ), listOf(
            ObjectPropertyDefinition(DMPDQV.IN_CATEGORY, inCategory),
            ObjectPropertyDefinition(DMPDQV.DERIVED_FROM, derivedFrom)
        ))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dimension

        if (inCategory != other.inCategory) return false
        if (title != other.title) return false

        return true
    }

    override fun hashCode(): Int {
        var result = inCategory?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Dimension(inCategory=$inCategory, title=$title, description=$description)"
    }


}