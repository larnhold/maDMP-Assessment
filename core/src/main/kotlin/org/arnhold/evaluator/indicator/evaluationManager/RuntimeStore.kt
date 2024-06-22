package org.arnhold.evaluator.indicator.evaluationManager

import org.springframework.stereotype.Component

@Component
class RuntimeStore {

    var store = mutableSetOf<EvaluationStoreItem>()

    fun storePackage(item: EvaluationStoreItem) {
        store.add(item)
    }

}