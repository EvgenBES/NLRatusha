package com.blackstone.data.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.text.DecimalFormat

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */

inline val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)