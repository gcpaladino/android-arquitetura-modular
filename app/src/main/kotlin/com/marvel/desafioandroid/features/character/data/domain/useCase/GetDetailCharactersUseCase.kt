package com.marvel.desafioandroid.features.character.data.domain.useCase

import com.marvel.desafioandroid.features.character.data.domain.Character
import com.marvel.desafioandroid.features.character.data.repository.CharacterRepository

class GetDetailCharactersUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(id: Long): Character {
        return repository.findById(id)
    }
}