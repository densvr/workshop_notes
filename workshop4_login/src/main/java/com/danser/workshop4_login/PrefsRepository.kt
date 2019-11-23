package com.danser.workshop4_login

import android.content.Context

class PrefsRepository(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveStringAsync(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String, default: String): String = prefs
        .getString(key, default) ?: default

    fun hasString(key: String, value: String): Boolean =
        prefs.contains(key) && getString(key, "") == value

    companion object {
        private const val PREFS_NAME = "workshop4_login_prefs"
    }
}
