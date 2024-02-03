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

    override suspend fun getUserById(id: Int, seed: String) = api.getUserById(id, seed)

    /*{
        return List(count) {
            User(
                name = Name(
                    first = "Karla",
                    last = "Christiansen",
                ),
                location = Location(
                    Street(
                        number = 4647,
                        name = "Guldagervej",
                    ),
                    city = "Kvistgaard",
                    state = "Danmark",
                    country = "Denmark",
                    postcode = 20911,
                ),
                cell = "61809747",
                picture = Picture(
                    medium = "https://randomuser.me/api/portraits/med/women/0.jpg",
                ),
            )
        }
    }*/
}