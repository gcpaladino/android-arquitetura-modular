package com.marvel.desafioandroid.features.character.view

import com.marvel.desafioandroid.features.character.data.domain.Character


data class CharacterViewState(
    val characters: List<Character>?,
    val character: Character?
)