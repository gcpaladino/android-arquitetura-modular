package com.marvel.desafioandroid.features.character.data.domain

import com.marvel.desafioandroid.dataLayer.data.remote.BaseResponse

class CharacterMapper {

    fun mapList(from: BaseResponse<CharacterResponse>) =
        from.data.results.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.desc,
                imageUrl = IMAGE_URL_FORMAT.format(
                    it.img.path.replace("http", "https"),
                    it.img.ext
                )
            )
        }

    fun mapDetail(from: BaseResponse<CharacterResponse>): Character {
        val characterResponse = from.data.results.first()
        return Character(
            id = characterResponse.id,
            name = characterResponse.name,
            description = characterResponse.desc,
            imageUrl = IMAGE_URL_FORMAT.format(
                characterResponse.img.path.replace("http", "https"),
                characterResponse.img.ext
            )
        )
    }

    companion object {
        private const val IMAGE_URL_FORMAT = "%s.%s"
    }
}