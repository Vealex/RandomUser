package com.cft.randomuser.repositories

import android.content.Context
import androidx.core.content.edit

class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    companion object {
        private const val SEED_VALUE = "SEED_VALUE"
    }

    private val preferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    override fun getSeed(): String? = preferences.getString(SEED_VALUE, null)

    override fun setSeed(seed: String) = preferences.edit {
        putString(SEED_VALUE, seed)
    }
}