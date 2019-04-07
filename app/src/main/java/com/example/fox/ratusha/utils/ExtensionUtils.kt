package com.example.fox.ratusha.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.text.DecimalFormat

/**
 * @author Evgeny Butov
 * @created 06.04.2019
 */

fun Double.twoCharAfterDot(): String{
    val formatText: DecimalFormat = DecimalFormat("#.##")
    return formatText.format(this)
}

inline val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)