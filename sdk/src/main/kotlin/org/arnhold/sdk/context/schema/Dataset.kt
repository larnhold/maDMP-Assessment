package org.arnhold.sdk.context.schema

import org.apache.jena.tdb.store.Hash
import org.arnhold.sdk.context.schema.enums.*
import java.util.*
import javax.security.auth.Subject
import kotlin.collections.HashSet

/**
*          @XmlElementRef(name = "creator", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "resulttype", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "language", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "country", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "subject", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "title", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "relevantdate", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "description", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "dateofacceptance", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "publisher", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "embargoenddate", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "source", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "format", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "contributor", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "resourcetype", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "coverage", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "bestaccessright", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "journal", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "pid", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "device", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "size", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "version", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "lastmetadataupdate", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "metadataversionnumber", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "originalId", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "collectedfrom", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "context", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "rels", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "instances", type = JAXBElement.class, required = false),
 *         @XmlElementRef(name = "citations", type = JAXBElement.class, required = false)
 */

class Dataset(
    var title: String? = null,
    var type: MutableSet<EDataType> = HashSet(),
    var size: Long? = null,
    var description: String? = null,
    var license: ELicense? = null,
    var startDate: Date? = null,
    var dataAccess: EDataAccessType? = null,
    var datasetId: Identifier? = null,
    var source: EDataSource? = null,
    var publusher: MutableSet<String> = HashSet(),
    var language: String? = null,
    var country: String? = null,
    var subject: MutableSet<String> = HashSet(),
    )
