package org.arnhold.dmpeval.casestudy.context.openAire

import generated.Response
import okhttp3.OkHttpClient
import okhttp3.Request
import org.arnhold.sdk.context.schema.Dataset
import org.arnhold.sdk.tools.XMLParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class OpenAireServiceImpl @Autowired constructor(
    val okHttpClient: OkHttpClient,
    val openAireMapperService: OpenAireMapperService,
    val xmlParser: XMLParser
) : OpenAireService {

    override fun findDatasetByDoi(doi: String): Dataset? {
        val request = Request.Builder()
            .url("http://api.openaire.eu/search/datasets?doi=$doi&format=xml")
            .get().build()

        try {
            val result = okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBodyString = response.body!!.string()
                    val responseEntity: Response = xmlParser.parseXmlToDto(responseBodyString, Response::class.java)
                    val mapped = openAireMapperService.toNormalizedDataset(doi, responseEntity, Dataset())

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
}
