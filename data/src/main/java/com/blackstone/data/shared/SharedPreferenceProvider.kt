package com.blackstone.data.shared

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Evgeny Butov
 * @created 09.11.2019
 */
abstract class SharedPreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    protected val sharedPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
}