package com.marvel.desafioandroid.features.character.data.repository

import com.marvel.desafioandroid.dataLayer.data.remote.BaseResponse
import com.marvel.desafioandroid.features.character.data.domain.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterAPI {

    @GET("/v1/public/characters")
    suspend fun list(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): BaseResponse<CharacterResponse>


    @GET("/v1/public/characters/{id}")
    suspend fun findById(
        @Path("id") id: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") timestamp: String
    ): BaseResponse<CharacterResponse>
}