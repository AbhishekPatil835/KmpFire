package org.android.kmpfire

import android.app.Application
import org.android.kmpfire.di.initCoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KmpFire: Application() {

    override fun onCreate() {
        super.onCreate()
        initCoin {
            androidContext(this@KmpFire)
        }
    }
}