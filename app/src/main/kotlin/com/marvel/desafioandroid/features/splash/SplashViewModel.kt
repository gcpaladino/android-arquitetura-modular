package com.marvel.desafioandroid.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    private val mutableCommand = MutableLiveData<SplashCommand>()
    val command: LiveData<SplashCommand> = mutableCommand

    fun init() {
        SplashCommand.OpenHome.run()
    }

    private fun SplashCommand.run() {
        mutableCommand.postValue(this)
    }

}
