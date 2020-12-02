package com.marvel.desafioandroid.features.character.data.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterResponse (
	@Json(name = "id") val id : Long,
	@Json(name = "name") val name : String,
	@Json(name = "description") val desc : String,
	@Json(name = "thumbnail") val img : CharImgResponse
): Parcelable