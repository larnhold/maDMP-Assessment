package org.arnhold.sdk.common.dqv

data class Dimension(
    val inCategory: Category?,
    val title: String?,
    val description: String?
) {
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
}