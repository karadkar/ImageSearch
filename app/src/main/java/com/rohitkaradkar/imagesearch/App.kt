package com.rohitkaradkar.imagesearch

import android.app.Application
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this, modules = listOf(
                androidModule, retrofitModule,
                remoteDataModule, searchActivityModule
            )
        )
    }
}