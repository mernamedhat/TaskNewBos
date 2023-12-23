package com.example.taskbos.prefrence

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TaskBos-pref", Context.MODE_PRIVATE)

    fun saveUserId(value: String) {
        preferences.edit().putString(Id, value).apply()
    }

    fun getUserId() = preferences.getString(Id, "0")
    fun savealbumId(value: Int) {
        preferences.edit().putInt("albumid", value).apply()
    }

    fun getalbumId() = preferences.getInt("albumid", 0)

    companion object {
        const val Id = "id_v"

    }

}