package com.cft.randomuser.mapper

import com.cft.randomuser.models.User
import com.cft.randomuser.models.UserUiModel
import java.text.SimpleDateFormat
import java.util.Locale

class UserUiModelMapper {

    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())

    fun map(user: User) = UserUiModel(
        gender = user.gender,
        title = user.name?.title,
        first = user.name?.first,
        last = user.name?.last,
        streetNumber = user.location?.street?.number,
        streetName = user.location?.street?.name,
        city = user.location?.city,
        state = user.location?.state,
        country = user.location?.country,
        postcode = user.location?.postcode,
        latitude = user.location?.coordinates?.latitude,
        longitude = user.location?.coordinates?.longitude,
        timezoneOffset = user.location?.timezone?.offset,
        timezoneDescription = user.location?.timezone?.description,
        email = user.email,
        uuid = user.login?.uuid,
        username = user.login?.username,
        password = user.login?.password,
        salt = user.login?.salt,
        md5 = user.login?.md5,
        sha1 = user.login?.sha1,
        sha256 = user.login?.sha256,
        dobDate = parser.parse(user.dob?.date)?.let { formatter.format(it) },
        dobAge = user.dob?.age,
        registerDate = parser.parse(user.registered?.date)?.let { formatter.format(it) },
        phone = user.phone,
        cell = user.cell,
        idName = user.id?.name,
        idValue = user.id?.value,
        large = user.picture?.large,
        medium = user.picture?.medium,
        thumbnail = user.picture?.thumbnail,
        nat = user.nat
    )
}