package com.blackstone.ratusha.di.injection


import android.content.Context
import com.blackstone.ratusha.di.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}