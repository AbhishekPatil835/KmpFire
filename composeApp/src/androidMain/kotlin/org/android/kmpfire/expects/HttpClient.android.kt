package org.android.kmpfire.expects

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
actual val httpClient: HttpClient = HttpClient(OkHttp){
    install(HttpTimeout) {
        socketTimeoutMillis = 60_000
        requestTimeoutMillis = 60_000
    }
    defaultRequest {
       contentType(ContentType.Application.Json)
        url("https://pokeapi.co/api/v2/")
    }
    install(ContentNegotiation){
        json(Json{
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        })
    }
    install(Logging){
        logger = Logger.ANDROID
        level = LogLevel.BODY
    }
}