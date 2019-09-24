package com.example.excryptotracker.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.excryptotracker.R
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class DelegatedPreferences<T>(val context: Context, val key: String, val defaultValue: T) {
    val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreferences(key, defaultValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        savePreference(key, value)
    }

    private fun findPreferences(key: String, defaultValue: T): T {
        with(prefs) {
            val result: Any? = when (defaultValue) {
                is Boolean -> getBoolean(key, defaultValue)
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                is String -> getString(key, defaultValue)
                else -> throw IllegalArgumentException()
            }
            return result as T
        }
    }

    private fun savePreference(key: String, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                else -> throw java.lang.IllegalArgumentException()
            }.apply()
        }
    }

    object PrefKey {
        val AUTO_UPDATE_STATUS = "AUTO_UPDATE_STATUS"
        val AUTO_UPDATE_DURATION = "AUTO_UPDATE_DURATION"
    }

}