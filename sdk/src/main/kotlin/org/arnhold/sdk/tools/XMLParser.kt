package org.arnhold.sdk.tools

import java.io.StringReader
import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException

class XMLParser {

    @Throws(JAXBException::class)
    fun <T> parseXmlToDto(xml: String, tClass: Class<T>): T {
        val jaxbContext = JAXBContext.newInstance(tClass)
        val unmarshaller = jaxbContext.createUnmarshaller()
        return unmarshaller.unmarshal(StringReader(xml)) as T
    }

}