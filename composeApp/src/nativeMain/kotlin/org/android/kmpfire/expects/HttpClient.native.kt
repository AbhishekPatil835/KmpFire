package org.android.kmpfire.expects

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import platform.Foundation.NSProcessInfo

actual val httpClient: HttpClient = HttpClient(Darwin)

actual object EnvironmentManager {
    actual fun getBaseUrl(): String {
       return NSProcessInfo.processInfo.environment["BASE_URL"] as? String?:"https://v2.jokeapi.dev"

    }
}