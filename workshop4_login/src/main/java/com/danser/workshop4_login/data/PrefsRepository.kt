package com.danser.workshop4_login.data

import android.content.Context

interface IPrefsRepository {
    fun saveStringAsync(key: String, value: String)
    fun getString(key: String, default: String): String
    fun hasString(key: String, value: String): Boolean
}

class PrefsRepository(context: Context): IPrefsRepository {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveStringAsync(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String, default: String): String = prefs
        .getString(key, default) ?: default

    override fun hasString(key: String, value: String): Boolean =
        prefs.contains(key) && getString(key, "") == value

    companion object {
        private const val PREFS_NAME = "workshop4_login_prefs"
    }
}
