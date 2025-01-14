package org.android.kmpfire.expects

import io.ktor.client.HttpClient

expect val httpClient: HttpClient

expect object EnvironmentManager {
    fun getBaseUrl(): String
}

