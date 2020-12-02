package com.marvel.desafioandroid.extension

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import com.marvel.desafioandroid.dataLayer.di.DataLayerModule
import com.marvel.desafioandroid.di.appModule

fun Application.startKoinApp() {
    startKoin {
        androidContext(this@startKoinApp)
        androidLogger()
        DataLayerModule.loadModules()
        loadKoinModules(appModule)
    }
}
