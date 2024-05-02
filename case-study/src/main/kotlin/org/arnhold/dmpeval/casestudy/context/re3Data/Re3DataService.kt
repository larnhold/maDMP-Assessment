package org.arnhold.dmpeval.casestudy.context.re3Data

import org.re3data.schema._2_2.Re3Data.Repository

interface Re3DataService {
    fun getHostByName(url: String): Repository?
}