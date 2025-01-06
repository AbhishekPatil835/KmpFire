package org.android.kmpfire

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.android.kmpfire.di.initCoin

fun main() {
    initCoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KmpFire",
        ) {
            App()
        }
    }
}