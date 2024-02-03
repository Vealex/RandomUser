package com.cft.randomuser.repositories

import com.cft.randomuser.models.ApiResponse

interface UserRepository {

    suspend fun getUsers(count: Int, seed: String? = null): ApiResponse

    suspend fun getUsersPage(page: Int, count: Int, seed: String): ApiResponse

    suspend fun getUserById(id: Int, seed: String): ApiResponse

}