package com.blackstone.ratusha.app


import android.app.Activity
import android.content.Context
import com.blackstone.ratusha.di.injection.AppComponent
import com.blackstone.ratusha.di.injection.DaggerAppComponent
import com.facebook.stetho.Stetho

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.blackstone.device.notification.Const.NOTIFICATION_FORPOST_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_FORPOST_TP
import com.blackstone.device.notification.Const.NOTIFICATION_OCTAL_CLOSE
import com.blackstone.device.notification.Const.NOTIFICATION_OCTAL_TP
import com.blackstone.ratusha.utils.CrashLogger
import com.google.firebase.messaging.FirebaseMessaging


class App : MultiDexApplication(), HasActivityInjector {

    companion object {
        lateinit var instance: App
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_FORPOST_CLOSE)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_FORPOST_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_OCTAL_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_OCTAL_CLOSE)

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()

        appComponent.inject(this)

        initCrashlytics()
    }

    private fun initCrashlytics() {
        CrashLogger.initialize(this)
    }
}
