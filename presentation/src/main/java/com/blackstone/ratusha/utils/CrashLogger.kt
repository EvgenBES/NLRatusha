package com.blackstone.ratusha.utils

import android.content.Context
import com.blackstone.ratusha.BuildConfig
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.PinningInfoProvider
import io.fabric.sdk.android.Fabric

object CrashLogger {

    val pinningInfoProvider: PinningInfoProvider?
        get() = if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.getPinningInfoProvider()
        } else null

    fun initialize(context: Context) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Fabric.with(context, Crashlytics())
        }
    }

    fun logException(priority: Int, tag: String, msg: String, throwable: Throwable) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.log(priority, tag, msg)
            Crashlytics.logException(throwable)
        }
    }

    fun log(msg: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.log(msg)
        }
    }

    fun log(priority: Int, tag: String, msg: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.log(priority, tag, msg)
        }
    }

    fun setUserIdentifier(identifier: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setUserIdentifier(identifier)
        }
    }

    fun setUserName(name: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setUserName(name)
        }
    }

    fun setUserEmail(email: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setUserEmail(email)
        }
    }

    fun setString(key: String, value: String) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setString(key, value)
        }
    }

    fun setBool(key: String, value: Boolean) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setBool(key, value)
        }
    }

    fun setDouble(key: String, value: Double) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setDouble(key, value)
        }
    }

    fun setFloat(key: String, value: Float) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setFloat(key, value)
        }
    }

    fun setInt(key: String, value: Int) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setInt(key, value)
        }
    }

    fun setLong(key: String, value: Long) {
        if (BuildConfig.ENABLE_CRASHLYTICS) {
            Crashlytics.setLong(key, value)
        }
    }
}

