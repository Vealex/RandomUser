package com.cft.randomuser.api

import com.cft.randomuser.models.ApiResponse
import kotlinx.serialization.json.Json
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


interface RandomUserGeneratorApi {

    @GET("api/1.4/")
    suspend fun getUsers(@Query("results") count: Int): ApiResponse

    @GET("api/1.4/")
    suspend fun getUsers(@Query("results") count: Int, @Query("seed") seed: String): ApiResponse

    @GET("api/1.4/")
    suspend fun getUsersPage(
        @Query("page") page: Int,
        @Query("results") count: Int,
        @Query("seed") seed: String
    ): ApiResponse

    @GET("api/1.4/?results=1")
    suspend fun getUserById(@Query("page") id: Int, @Query("seed") seed: String): ApiResponse

    companion object {
        private val json = Json { ignoreUnknownKeys = true }
        val INSTANCE: RandomUserGeneratorApi by lazy {
            RetrofitFactory.INSTANCE.create()
        }
    }
}