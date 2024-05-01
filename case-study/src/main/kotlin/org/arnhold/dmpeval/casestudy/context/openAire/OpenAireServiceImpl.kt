package org.arnhold.dmpeval.casestudy.context.openAire

import generated.Response
import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import okhttp3.OkHttpClient
import okhttp3.Request
import org.arnhold.sdk.context.schema.Dataset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.StringReader


@Service
class OpenAireServiceImpl @Autowired constructor(
    val okHttpClient: OkHttpClient,
    val openAireMapperService: OpenAireMapperService
) : OpenAireService {

    override fun findDatasetByDoi(doi: String): Dataset? {
        val request = Request.Builder()
            .url("http://api.openaire.eu/search/datasets?doi=$doi&format=xml")
            .get().build()

        try {
            val result = okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBodyString = response.body!!.string()
                    val responseEntity: Response = parseXmlToDto(responseBodyString, Response::class.java)
                    val mapped = openAireMapperService.mapAtoB(doi, responseEntity, Dataset())

                    return@use mapped
                } else {
                    return@use null
                }
            }

            return result
        } catch (e: Exception) {
            return null
        }
    }

    @Throws(JAXBException::class)
    private fun <T> parseXmlToDto(xml: String, tClass: Class<T>): T {
        val jaxbContext = JAXBContext.newInstance(tClass)
        val unmarshaller = jaxbContext.createUnmarshaller()
        return unmarshaller.unmarshal(StringReader(xml)) as T
    }
}
