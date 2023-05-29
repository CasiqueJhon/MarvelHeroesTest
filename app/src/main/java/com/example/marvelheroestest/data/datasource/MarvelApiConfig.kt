package com.example.marvelheroestest.data.datasource

import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class MarvelApiConfig @Inject constructor(
    private val apiKeyProvider: ApiKeyProvider
) {

    fun getApiKey(): String {
        return apiKeyProvider.getApiKey()
    }

    fun getTimestamp(): String {
        val timestamp = System.currentTimeMillis().toString()
        return timestamp
    }

    fun getHash(timestamp: String, privateKey: String): String {
        val input = timestamp + privateKey + getApiKey()
        val hash = input.md5()
        return hash
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(this.toByteArray())
        val digest = md.digest()
        return BigInteger(1, digest).toString(16).padStart(32, '0')
    }

    fun getPrivateKey(): String {
        return apiKeyProvider.getMarvelPrivateKey()
    }

}