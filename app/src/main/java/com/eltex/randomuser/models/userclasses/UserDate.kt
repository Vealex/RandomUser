package com.eltex.randomuser.models.userclasses

import java.time.Instant

data class UserDate(
    val date: Instant? = null,
    val age: Int? = null,
)
