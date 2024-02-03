package com.cft.randomuser.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object RetrofitFactory {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    private val mimeType = MediaType.get("application/json")
    val INSTANCE: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
//            .client(
//                OkHttpClient.Builder()
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .build()
//            )
            .addConverterFactory(
                json.asConverterFactory(mimeType)
            )
            .build()
    }
}