package org.android.kmpfire.expects

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

actual val httpClient: HttpClient = HttpClient(CIO)


actual object EnvironmentManager {
    actual fun getBaseUrl(): String {
        return System.getenv("BASE_URL") ?: "https://v2.jokeapi.dev"
    }
}