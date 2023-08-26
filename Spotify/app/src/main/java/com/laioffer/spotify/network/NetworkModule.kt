package com.laioffer.spotify.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// static
object NetworkModule {
    // 0.0.0.0:8080 / localhost
    private const val BASE_URL = "http://10.0.2.2:8080/"

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
}