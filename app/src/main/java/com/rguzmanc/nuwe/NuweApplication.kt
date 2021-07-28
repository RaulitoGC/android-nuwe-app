package com.rguzmanc.nuwe

import android.app.Application
import com.rguzmanc.nuwe.di.presentationModule
import com.rguzmanc.nuwe.utils.TimberLoggingTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class NuweApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initInjector()
        initLogger()
    }

    private fun initInjector(){
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NuweApplication)
            modules( presentationModule)
        }
    }

    private fun initLogger() {
        Timber.plant(TimberLoggingTree())
    }
}