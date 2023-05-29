package com.example.marvelheroestest.domain.usecase

import com.example.marvelheroestest.data.datasource.MarvelApiConfig
import com.example.marvelheroestest.data.network.ApiInterface
import com.example.marvelheroestest.data.model.Character
import java.lang.Exception
import com.example.marvelheroestest.data.datasource.Result
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val apiInterface: ApiInterface,
    private val marvelApiConfig: MarvelApiConfig
    ) {

    suspend fun execute(): Result<List<Character>> {
        return try {
            val apiKey = marvelApiConfig.getApiKey()
            val timestamp = marvelApiConfig.getTimestamp()
            val hash = marvelApiConfig.getHash(timestamp, marvelApiConfig.getPrivateKey())

            val characterResponse = apiInterface.getCharacters(apiKey, timestamp, hash)
            val charactersList = characterResponse.data?.results ?: emptyList()
            Result.Success(charactersList)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}