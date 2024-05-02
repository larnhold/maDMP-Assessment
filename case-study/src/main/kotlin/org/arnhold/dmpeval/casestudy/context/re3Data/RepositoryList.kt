package org.arnhold.dmpeval.casestudy.context.re3Data

import jakarta.xml.bind.annotation.XmlAttribute
import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement


@XmlRootElement(name = "list")
class RepositoryList {
    @get:XmlElement(name = "repository")
    var repositories: List<Repository> = ArrayList()

    @XmlRootElement(name = "repository")
    class Repository {
        @get:XmlElement
        var id: String? = null

        @get:XmlElement
        var doi: String? = null

        @get:XmlElement
        var name: String? = null

        @get:XmlElement
        var link: Link? = null
    }

    class Link {
        @get:XmlAttribute
        var href: String? = null

        @get:XmlAttribute
        var rel: String? = null
    }
}