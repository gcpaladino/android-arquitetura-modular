package com.marvel.desafioandroid.dataLayer.data.remote

data class BaseResponse<T>(
    val code: Any,
    val status: String,
    val data: DataResponse<T>
)