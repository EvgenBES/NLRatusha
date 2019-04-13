package com.blackstone.ratusha.utils

import android.content.Context
import android.util.DisplayMetrics


object DisplayUtils {

   fun getDensityDouble(context: Context): Double {
      val displayMetrics = context.resources.displayMetrics

      return when (displayMetrics.densityDpi) {
         DisplayMetrics.DENSITY_LOW -> 0.75
         DisplayMetrics.DENSITY_MEDIUM -> 1.0
         DisplayMetrics.DENSITY_HIGH -> 1.5
         DisplayMetrics.DENSITY_XHIGH -> 2.0
         DisplayMetrics.DENSITY_XXHIGH -> 3.0
         DisplayMetrics.DENSITY_XXXHIGH -> 4.0
         else -> 1.0
      }
   }

}