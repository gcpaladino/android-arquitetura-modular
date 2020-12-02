package com.marvel.desafioandroid.features.character.view

sealed class CharacterCommand {
    object showLoading : CharacterCommand()
    object hideLoading : CharacterCommand()
}