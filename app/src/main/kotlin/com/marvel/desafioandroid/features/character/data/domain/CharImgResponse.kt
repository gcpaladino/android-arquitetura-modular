package com.marvel.desafioandroid.features.character.data.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharImgResponse(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val ext: String
): Parcelable