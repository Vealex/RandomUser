package com.eltex.randomuser.models

import com.eltex.randomuser.models.userclasses.Id
import com.eltex.randomuser.models.userclasses.Location
import com.eltex.randomuser.models.userclasses.Login
import com.eltex.randomuser.models.userclasses.Name
import com.eltex.randomuser.models.userclasses.Picture
import com.eltex.randomuser.models.userclasses.UserDate

data class User(
    val gender: String? = null,
    val name: Name? = null,
    val location: Location? = null,
    val email: String? = null,
    val login: Login? = null,
    val registered: UserDate? = null,
    val dob: UserDate? = null,
    val phone: String? = null,
    val cell: String? = null,
    val id: Id? = null,
    val picture: Picture? = null,
    val nat: String? = null,
)
