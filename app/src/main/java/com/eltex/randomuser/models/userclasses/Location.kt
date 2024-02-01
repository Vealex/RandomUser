package com.eltex.randomuser.models.userclasses

data class Location(
    val street: Street? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postcode: Int? = null,
    val coordinates: Coordinates? = null,
    val timezone: UserTimeZone? = null,
)
