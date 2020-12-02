package com.marvel.desafioandroid.dataLayer.data.remote

data class DataResponse<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)