package org.arnhold.evaluator.dataProvision

import org.arnhold.evaluator.tripleStore.TripleStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DataProviderServiceImpl @Autowired constructor(val tripleStoreService: TripleStoreService) : DataProviderService {
}