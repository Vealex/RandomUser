package com.cft.randomuser.repositories

interface PreferencesRepository {

    fun getSeed(): String?

    fun setSeed(seed: String)

}