package org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiEvaluatePayload
import org.arnhold.dmpeval.casestudy.evaluation.qualityOfActionsCategoryEvaluators.model.fuji.FujiRoot
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.TimeUnit


@Component
class FujiServiceImpl: FujiService {

    private val logger = KotlinLogging.logger {}

    companion object {
        val MEDIA_TYPE_JSON = "application/json".toMediaType()
        const val FUJI_USERNAME = "marvel"
        const val FUJI_PASSWORD = "wonderwoman"
        const val FUJI_ADDRESS = "http://localhost:1071/fuji/api/v1"
    }

    override fun evaluateResource(resource: String): FujiRoot? {
        val client: OkHttpClient =  OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(FixContentTypeInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val writer = ObjectMapper().writer().withDefaultPrettyPrinter()
        val reader = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val requestObj = FujiEvaluatePayload(object_identifier = resource)
        val requestObjStr = writer.writeValueAsString(requestObj)
        logger.info { "JSON for F-UJI evaluator: $requestObjStr" }
        val postBody = requestObjStr.toRequestBody(MEDIA_TYPE_JSON)

        val request = Request.Builder()
            .url("$FUJI_ADDRESS/evaluate")
            .addHeader("Authorization", Credentials.basic(FUJI_USERNAME, FUJI_PASSWORD))
            .post(postBody).build()

        logger.info { request.toString() }

        try {
            logger.info { "Start F-UJI evaluation of $resource" }
            val result = client.newCall(request).execute().use { response ->
                logger.info { "F-UJI response code: ${response.code}" }
                if (response.isSuccessful) {
                    val responseBodyString = response.body!!.string()
                    logger.info { responseBodyString}
                    return@use reader.readValue(responseBodyString, FujiRoot::class.java)
                } else {
                    return@use null
                }
            }
            return result
        } catch (e: Exception) {
            logger.error { e }
            return null
        }
    }

    class FixContentTypeInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val fixedRequest = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .build()
            return chain.proceed(fixedRequest)
        }
    }
}