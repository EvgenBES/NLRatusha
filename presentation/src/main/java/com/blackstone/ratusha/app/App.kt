package com.blackstone.ratusha.app


import android.app.Activity
import android.content.Context
import android.util.Log
import com.blackstone.ratusha.di.injection.AppComponent
import com.blackstone.ratusha.di.injection.DaggerAppComponent
import com.facebook.stetho.Stetho

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.blackstone.ratusha.utils.Const.FORPOST_CLOSE
import com.blackstone.ratusha.utils.Const.FORPOST_TP
import com.blackstone.ratusha.utils.Const.OCTAL_CLOSE
import com.blackstone.ratusha.utils.Const.OCTAL_TP
import com.blackstone.ratusha.utils.CrashLogger
import com.google.firebase.iid.FirebaseInstanceId
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
        FirebaseMessaging.getInstance().subscribeToTopic(FORPOST_CLOSE)
        FirebaseMessaging.getInstance().subscribeToTopic(FORPOST_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(OCTAL_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(OCTAL_CLOSE)

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
