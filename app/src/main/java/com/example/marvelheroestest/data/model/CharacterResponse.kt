package com.example.marvelheroestest.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data") val data: CharacterData
)

data class CharacterData(
    @SerializedName("results") val results: List<Character> = emptyList()
)

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
)

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)