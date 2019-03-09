package com.example.fox.ratusha.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


inline val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)