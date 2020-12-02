package com.marvel.desafioandroid

import com.marvel.desafioandroid.extension.startKoinApp

class DesafioAPP : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoinApp()
    }

}