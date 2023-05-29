package com.example.marvelheroestest.data.datasource

import android.content.Context
import com.example.marvelheroestest.R
import java.io.InputStream
import java.util.*
import javax.inject.Inject

class ApiKeyProvider @Inject constructor(
    private val context: Context
) {

    fun getApiKey(): String {
        val properties = Properties()
        val inputStream: InputStream = context.assets.open("config.properties")
        properties.load(inputStream)
        return properties.getProperty("api_key")
    }

    fun getMarvelPrivateKey(): String {
        val properties = Properties()
        val inputStream: InputStream = context.assets.open("config.properties")
        properties.load(inputStream)
        return properties.getProperty("private_key")
    }
}