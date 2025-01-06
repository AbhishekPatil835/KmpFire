package org.android.kmpfire

import androidx.compose.ui.window.ComposeUIViewController
import org.android.kmpfire.di.initCoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initCoin()
    }
) {
    App()
}