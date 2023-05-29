package com.example.marvelheroestest.data.network

import com.example.marvelheroestest.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): CharacterResponse
}