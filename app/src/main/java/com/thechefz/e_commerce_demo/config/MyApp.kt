package com.thechefz.e_commerce_demo.config

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)


            koin.loadModules(listOf(
                viewModelModule,
                repoModule,
                remoteDSModule,
                useCaseModule
            ))


        }
    }
}