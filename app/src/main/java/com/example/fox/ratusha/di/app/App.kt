package com.example.fox.ratusha.di.app


import android.app.Activity
import android.app.Application
import com.example.fox.ratusha.di.injection.AppComponent
import com.example.fox.ratusha.di.injection.DaggerAppComponent
import com.facebook.stetho.Stetho


import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

class App : Application(), HasActivityInjector {

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

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)


        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

}
