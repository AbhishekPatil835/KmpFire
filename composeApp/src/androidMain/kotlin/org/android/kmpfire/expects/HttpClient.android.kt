package org.android.kmpfire.expects

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.android.kmpfire.BuildConfig

actual val httpClient: HttpClient = HttpClient(OkHttp)

actual object EnvironmentManager {
    actual fun getBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}