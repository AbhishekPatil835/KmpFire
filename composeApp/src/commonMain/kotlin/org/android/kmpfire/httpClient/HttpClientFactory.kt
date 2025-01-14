package org.android.kmpfire.httpClient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.android.kmpfire.expects.EnvironmentManager
import org.android.kmpfire.expects.httpClient

@OptIn(ExperimentalSerializationApi::class)
class HttpClientFactory(private val environmentManager: EnvironmentManager) {

    fun createHttpClient() : HttpClient {
        return httpClient.config {
                install(HttpTimeout) {
                    socketTimeoutMillis = 60_000
                    requestTimeoutMillis = 60_000
                }
                defaultRequest {
                    contentType(ContentType.Application.Json)
                    url{
                        host = getBaseUrl().removePrefix("https://").removePrefix("http://")
                        protocol = URLProtocol.HTTPS

                    }
                }
                install(ContentNegotiation){
                    json(Json{
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    })
                }
                install(Logging){
                    logger = Logger.SIMPLE
                    level = LogLevel.BODY
                }
        }
    }

    private fun getBaseUrl() : String {
        return environmentManager.getBaseUrl()
    }
}