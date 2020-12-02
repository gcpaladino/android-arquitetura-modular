package com.marvel.desafioandroid.features.character.data.repository

import com.marvel.desafioandroid.dataLayer.BuildConfig
import com.marvel.desafioandroid.dataLayer.data.remote.BaseResponse
import com.marvel.desafioandroid.dataLayer.extension.toMD5
import com.marvel.desafioandroid.features.character.data.domain.CharacterResponse
import com.marvel.desafioandroid.features.character.data.domain.Character
import com.marvel.desafioandroid.features.character.data.domain.CharacterMapper

class CharacterRepository(
    private val api: CharacterAPI,
    private val mapper: CharacterMapper
) {
    suspend fun list(offset: Int, limit: Int): List<Character> {
        val timestamp = System.currentTimeMillis().toString()
        return mapper.mapList(api.list(
            apiKey = PUBLIC_KEY,
            hash = apiSec(timestamp),
            timestamp = timestamp,
            offset = offset,
            limit = limit))
    }

    suspend fun findById(id: Long): Character {
        val timestamp = System.currentTimeMillis().toString()
        return mapper.mapDetail(api.findById(
            id = id,
            apiKey = PUBLIC_KEY,
            hash = apiSec(timestamp),
            timestamp = timestamp))
    }

    companion object {
        private const val PUBLIC_KEY = BuildConfig.MARVEL_API_KEY_PUBLIC
        private const val PRIVATE_KEY = BuildConfig.MARVEL_API_KEY_PRIVATE
        private const val HASH_FORMAT = "%s%s%s"
    }

    private fun apiSec(timestamp: String) =
        HASH_FORMAT.format(timestamp, PRIVATE_KEY, PUBLIC_KEY).toMD5()
}