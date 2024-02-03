package com.cft.randomuser.viewmodel

import com.cft.randomuser.errors.UserStatement
import com.cft.randomuser.models.UserUiModel

data class UserUiState(
    val user: UserUiModel? = null,
    val statement: UserStatement? = null,
)
