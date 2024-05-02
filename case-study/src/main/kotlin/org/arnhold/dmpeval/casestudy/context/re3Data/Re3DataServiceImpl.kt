package org.arnhold.dmpeval.casestudy.context.re3Data

import okhttp3.OkHttpClient
import okhttp3.Request
import org.arnhold.sdk.tools.XMLParser
import org.jvnet.hk2.annotations.Service
import org.re3data.schema._2_2.Re3Data
import org.re3data.schema._2_2.Re3Data.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Service
@Component
class Re3DataServiceImpl @Autowired constructor(
    val okHttpClient: OkHttpClient,
    val xmlParser: XMLParser
) : Re3DataService {

    override fun getHostByName(name: String): Repository? {
        val request = Request.Builder()
            .url("https://www.re3data.org/api/v1/repositories")
            .get().build()

        try {
            val result = okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBodyString = response.body!!.string()
                    val responseEntity: RepositoryList = xmlParser.parseXmlToDto(responseBodyString, RepositoryList::class.java)

                    return@use responseEntity
                } else {
                    return@use null
                }
            }

            val repo = result?.repositories?.find { repository -> repository.name?.contains(name) ?: false }

            return if (repo?.id != null) {
                this.getById(repo.id!!)
            } else {
                null
            }
        } catch (e: Exception) {
            return null
        }
    }

    private fun getById(id: String): Repository? {
        val request = Request.Builder()
            .url("https://www.re3data.org/api/v1/repository/$id")
            .get().build()

        try {
            val result = okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBodyString = response.body!!.string()
                    val responseEntity: Re3Data = xmlParser.parseXmlToDto(responseBodyString, Re3Data::class.java)

                    return@use responseEntity.repository.first()
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