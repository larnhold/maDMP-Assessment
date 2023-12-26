package org.arnhold.dmpeval.casestudy.openaire

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.apache.jena.rdf.model.Model
import org.arnhold.dmpeval.casestudy.context.model.api.Entity
import org.springframework.stereotype.Service
import org.w3c.dom.Document
import org.xml.sax.SAXException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


@Service
class OpenAireServiceImpl: OpenAireService {
    // https://graph.openaire.eu/develop/api.html#projects
    // https://api.openaire.eu/vocabularies/
    // file:///home/lukas/Downloads/OpenAIRE2020%20D8.2%20-%20LOD%20Services.pdf

    override fun test(madmp: Model): String {
        return this.getResearchData(madmp)
    }

    override fun getPublications(madmp: Model) {
    }

    override fun getResearchData(madmp: Model): String {
        val url = "http://api.openaire.eu/search/researchProducts?doi=10.5281/zenodo.4701612&format=xml"

        val httpClient = HttpClients.createDefault()
        val httpGet = HttpGet(url)
        var xmlResponse: String

        try {
            httpClient.execute(httpGet).use { response ->
                val entity = response.entity
                xmlResponse = EntityUtils.toString(entity)

                val factory = DocumentBuilderFactory.newInstance()
                val builder = factory.newDocumentBuilder()

                val doc = builder.parse(ByteArrayInputStream(xmlResponse.toByteArray(StandardCharsets.UTF_8)))
                val test = doc.getElementsByTagName("oaf:entity")
                val node = test.item(0)

                val newXml = builder.newDocument()
                val importedNode = newXml.importNode(node, true)
                newXml.appendChild(importedNode)
                val entityString = getStringFromDocument(newXml)!!

                val resultEntity: Entity = parseXmlToDto(entityString, Entity::class.java)
                return entityString
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: ParserConfigurationException) {
            throw RuntimeException(e)
        } catch (e: SAXException) {
            throw RuntimeException(e)
        } catch (e: JAXBException) {
            throw RuntimeException(e)
        }
    }

    private fun getStringFromDocument(doc: Document): String? {
        try {
            val domSource = DOMSource(doc)
            val writer = StringWriter()
            val result = StreamResult(writer)
            val tf = TransformerFactory.newInstance()
            val transformer = tf.newTransformer()
            transformer.transform(domSource, result)
            return writer.toString()
        } catch (ex: TransformerException) {
            ex.printStackTrace()
            return null
        }
    }

    override fun getResearchSoftware(madmp: Model) {
    }

    override fun getResearchProducts(madmp: Model) {
    }

    @Throws(JAXBException::class)
    private fun <T> parseXmlToDto(xml: String, tClass: Class<T>): T {
        val jaxbContext = JAXBContext.newInstance(tClass)
        val unmarshaller = jaxbContext.createUnmarshaller()
        return unmarshaller.unmarshal(StringReader(xml)) as T
    }
}
