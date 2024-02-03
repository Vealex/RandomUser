package com.cft.randomuser.repositories

import com.cft.randomuser.api.RandomUserGeneratorApi
import com.cft.randomuser.models.ApiResponse

class NetworkUserRepository(private val api: RandomUserGeneratorApi) : UserRepository {
    override suspend fun getUsers(count: Int, seed: String?): ApiResponse {
        return if (seed.isNullOrEmpty()) {
            api.getUsers(count)
        } else {
            api.getUsers(count, seed)
        }
    }

    override suspend fun getUsersPage(page: Int, count: Int, seed: String) =
        api.getUsersPage(page, count, seed)
}