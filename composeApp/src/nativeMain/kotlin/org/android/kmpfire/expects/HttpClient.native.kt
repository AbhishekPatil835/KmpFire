package org.android.kmpfire.expects

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual val httpClient: HttpClient = HttpClient(Darwin) {
    install(HttpTimeout){
        socketTimeoutMillis = 60000
        requestTimeoutMillis = 60000
    }
    defaultRequest {
        contentType(ContentType.Application.Json)
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
}