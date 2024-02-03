package com.cft.randomuser.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("gender")
    val gender: String = "",
    @SerialName("name")
    val name: Name? = null,
    @SerialName("location")
    val location: Location? = null,
    @SerialName("email")
    val email: String = "",
    @SerialName("login")
    val login: Login? = null,
    @SerialName("registered")
    val registered: UserDate? = null,
    @SerialName("dob")
    val dob: UserDate? = null,
    @SerialName("phone")
    val phone: String = "",
    @SerialName("cell")
    val cell: String = "",
    @SerialName("id")
    val id: Id? = null,
    @SerialName("picture")
    val picture: Picture? = null,
    @SerialName("nat")
    val nat: String = "",
)

@Serializable
data class Name(
    @SerialName("title")
    val title: String = "",
    @SerialName("first")
    val first: String = "",
    @SerialName("last")
    val last: String = "",
)

@Serializable
data class Location(
    @SerialName("street")
    val street: Street? = null,
    @SerialName("city")
    val city: String = "",
    @SerialName("state")
    val state: String = "",
    @SerialName("country")
    val country: String = "",
    @SerialName("postcode")
    val postcode: String = "",
    @SerialName("coordinates")
    val coordinates: Coordinates? = null,
    @SerialName("timezone")
    val timezone: UserTimeZone? = null,
)

@Serializable
data class Street(
    @SerialName("number")
    val number: Int? = null,
    @SerialName("name")
    val name: String = "",
)

@Serializable
data class Coordinates(
    @SerialName("latitude")
    val latitude: String = "",
    @SerialName("longitude")
    val longitude: String = "",
)

@Serializable
data class UserTimeZone(
    @SerialName("offset")
    val offset: String = "",
    @SerialName("description")
    val description: String = "",
)

@Serializable
data class Login(
    @SerialName("uuid")
    val uuid: String = "",
    @SerialName("username")
    val username: String = "",
    @SerialName("password")
    val password: String = "",
    @SerialName("salt")
    val salt: String = "",
    @SerialName("md5")
    val md5: String = "",
    @SerialName("sha1")
    val sha1: String = "",
    @SerialName("sha256")
    val sha256: String = "",
)

@Serializable
data class UserDate(
    @SerialName("date")
    val date: String? = null,
    @SerialName("age")
    val age: Int? = null,
)

@Serializable
data class Id(
    @SerialName("name")
    val name: String = "",
    @SerialName("value")
    val value: String? = null,
)

@Serializable
data class Picture(
    @SerialName("large")
    val large: String = "",
    @SerialName("medium")
    val medium: String = "",
    @SerialName("thumbnail")
    val thumbnail: String = "",
)
