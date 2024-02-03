package com.cft.randomuser.viewmodel

import com.cft.randomuser.errors.UserListStatement
import com.cft.randomuser.models.UserUiModel

data class UserListUiState(
    val users: List<UserUiModel> = emptyList(),
    val seed: String? = null,
    val page: Int = 0,
    val statement: UserListStatement? = null,
)