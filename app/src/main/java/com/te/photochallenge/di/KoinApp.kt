package com.te.photochallenge.di

import android.app.Application
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger((if (BuildConfig.DEBUG) org.koin.core.logger.Level.ERROR else org.koin.core.logger.Level.NONE))
            androidContext(this@KoinApp)
            modules(listOf(dataModule))
        }
    }
}