package org.arnhold.sdk.common.dqv

data class Category(
    val description: String,
    val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        return title == other.title
    }

    override fun hashCode(): Int {
        return title.hashCode()
    }
}
