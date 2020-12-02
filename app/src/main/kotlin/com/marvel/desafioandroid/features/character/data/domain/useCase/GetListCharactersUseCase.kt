package com.marvel.desafioandroid.features.character.data.domain.useCase

import com.marvel.desafioandroid.features.character.data.repository.CharacterRepository
import com.marvel.desafioandroid.features.character.data.domain.Character

class GetListCharactersUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(offset: Int, limit: Int): List<Character> {
        return repository.list(offset, limit)
    }
}