package com.dex.movieapp.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    val PREFS_FILE_NAME = "com.sky.skyqgo.prefs"
    val CACHE_TIME = "cache_time"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, 0)


    var cacheTime: Long
        get() = prefs.getLong(CACHE_TIME, 0)
        set(value) = prefs.edit().putLong(CACHE_TIME, value).apply()

}