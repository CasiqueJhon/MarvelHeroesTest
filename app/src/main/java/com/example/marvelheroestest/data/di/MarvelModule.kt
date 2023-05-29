package com.example.marvelheroestest.data.di

import android.app.Application
import android.content.Context
import com.example.marvelheroestest.data.datasource.ApiKeyProvider
import com.example.marvelheroestest.data.datasource.Config
import com.example.marvelheroestest.data.datasource.MarvelApiConfig
import com.example.marvelheroestest.data.network.ApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object MarvelModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideApiKeyProvider(context: Context): ApiKeyProvider  {
        return ApiKeyProvider(context)
    }

    @Provides
    fun provideMarvelApiConfig(apikeyProvider: ApiKeyProvider): MarvelApiConfig {
        return MarvelApiConfig(apikeyProvider)
    }
}