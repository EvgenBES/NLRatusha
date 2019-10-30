package com.blackstone.ratusha.app


import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.blackstone.ratusha.di.injection.AppComponent
import com.blackstone.ratusha.di.injection.DaggerAppComponent

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
import com.blackstone.ratusha.R
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

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        initDI()
        initCrashlytics()
        initFirebaseMessaging()

    }

    private fun initDI() {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }

    private fun initCrashlytics() {
        CrashLogger.initialize(this)
    }

    private fun initFirebaseMessaging() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_FORPOST_CLOSE)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_FORPOST_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_OCTAL_TP)
        FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_OCTAL_CLOSE)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.default_notification_channel_id)
            val descriptionText = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.default_notification_channel_id), name, importance).apply {
                description = descriptionText
            }

            //Setting channel
            channel.enableLights(true)
            channel.enableVibration(true)

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
