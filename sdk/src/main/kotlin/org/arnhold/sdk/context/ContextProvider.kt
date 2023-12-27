package org.arnhold.sdk.context

interface ContextProvider<T, Q> {

    fun getContext(query: Q): T
}