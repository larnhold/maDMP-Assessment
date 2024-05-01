package org.arnhold.sdk.context.schema

import org.arnhold.sdk.context.schema.enums.*
import java.util.*
import kotlin.collections.HashSet

class Dataset(
    var id: Long? = null,
    var title: String? = null,
    var type: MutableSet<EDataType> = HashSet(),
    var size: Long? = null,
    var description: String? = null,
    var license: ELicense? = null,
    var startDate: Date? = null,
    var dataAccess: EDataAccessType? = null,
    var datasetId: Identifier? = null,
    var source: EDataSource? = null
)
