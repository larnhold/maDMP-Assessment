package org.arnhold.sdk.common.dqv

data class Dimension(
    val inCategory: Category?,
    val inDimension: Dimension?,
    val title: String?,
    val description: String?
)