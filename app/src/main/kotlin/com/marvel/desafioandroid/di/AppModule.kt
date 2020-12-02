package com.marvel.desafioandroid.di

import com.marvel.desafioandroid.dataLayer.data.remote.HttpClient
import com.marvel.desafioandroid.features.character.data.repository.CharacterAPI
import com.marvel.desafioandroid.features.character.data.repository.CharacterRepository
import com.marvel.desafioandroid.features.character.data.domain.useCase.GetDetailCharactersUseCase
import com.marvel.desafioandroid.features.character.data.domain.useCase.GetListCharactersUseCase
import com.marvel.desafioandroid.features.character.data.domain.CharacterMapper
import com.marvel.desafioandroid.features.character.view.CharacterViewModel
import com.marvel.desafioandroid.features.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { CharacterMapper() }
    factory { CharacterRepository(get(), get()) }
    factory { get<HttpClient>().create(CharacterAPI::class.java) }

    factory { GetListCharactersUseCase(get()) }
    factory { GetDetailCharactersUseCase(get()) }

    viewModel { SplashViewModel() }
    viewModel {
        CharacterViewModel(
            get(),
            get()
        )
    }
}
