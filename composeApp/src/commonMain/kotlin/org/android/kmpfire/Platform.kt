package org.android.kmpfire

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform