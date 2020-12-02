package com.marvel.desafioandroid.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.marvel.desafioandroid.features.character.view.CharacterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.init()
    }

    private fun setupObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is SplashCommand.OpenHome -> openHome()
            }
        })
    }

    private fun openHome() {
        startActivity(CharacterActivity.intent(this))
        finish()
    }
}
