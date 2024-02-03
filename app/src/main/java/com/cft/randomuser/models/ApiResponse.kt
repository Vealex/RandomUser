package com.cft.randomuser.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerialName("results")
    val results: List<User>,
    @SerialName("info")
    val info: ApiResponseInfo? = null,
)

@Serializable
data class ApiResponseInfo(
    @SerialName("seed")
    val seed: String = "",
    @SerialName("page")
    val page: Int,
)
