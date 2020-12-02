package com.marvel.desafioandroid.features.character.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvel.desafioandroid.dataLayer.data.remote.SafeResponse
import com.marvel.desafioandroid.dataLayer.data.remote.safeRequest
import com.marvel.desafioandroid.features.character.data.domain.useCase.GetDetailCharactersUseCase
import com.marvel.desafioandroid.features.character.data.domain.useCase.GetListCharactersUseCase
import com.marvel.desafioandroid.features.character.data.domain.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterViewModel(
    private val getListCharactersUseCase: GetListCharactersUseCase,
    private val getDetailCharactersUseCase: GetDetailCharactersUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.Main + SupervisorJob()
) : ViewModel() {

    private val mutableViewState = MutableLiveData<CharacterViewState>()
    val viewState: LiveData<CharacterViewState> = mutableViewState

    private val mutableCommand = MutableLiveData<CharacterCommand>()
    val command: LiveData<CharacterCommand> = mutableCommand

    fun loadCharacters(offset: Int, limit: Int) {
        viewModelScope.launch(dispatcher) {
            CharacterCommand.showLoading.run()

            when (val response = safeRequest { getListCharactersUseCase(offset, limit) }) {
                is SafeResponse.Success -> onCharactersSuccess(response.value)
                is SafeResponse.GenericError -> onGenericError()
                is SafeResponse.NetworkError -> onNetworkError()
            }

            CharacterCommand.hideLoading.run()
        }
    }

    fun loadCharacterDetail(id: Long) {
        viewModelScope.launch(dispatcher) {
            CharacterCommand.showLoading.run()

            when (val response = safeRequest { getDetailCharactersUseCase(id) }) {
                is SafeResponse.Success -> onCharacterDetaukSuccess(response.value)
                is SafeResponse.GenericError -> onGenericError()
                is SafeResponse.NetworkError -> onNetworkError()
            }

            CharacterCommand.hideLoading.run()
        }
    }

    private fun onCharactersSuccess(results: List<Character>) {
        CharacterViewState(
            characters = results,
            character = null
        ).run()
    }

    private fun onCharacterDetaukSuccess(result: Character) {
        CharacterViewState(
            characters = null,
            character = result
        ).run()
    }


    private fun onGenericError(){
        //TODO - Implement command open dialog by generic error - Giuliano Paladino
        CharacterCommand.hideLoading.run()
    }

    private fun onNetworkError(){
        //TODO - Implement command open dialog by network error - Giuliano Paladino
        CharacterCommand.hideLoading.run()
    }

    private fun CharacterViewState.run() {
        mutableViewState.postValue(this)
    }

    private fun CharacterCommand.run() {
        mutableCommand.postValue(this)
    }

}
