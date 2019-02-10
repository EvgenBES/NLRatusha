package com.example.fox.ratusha.di.injection


import android.content.Context
import com.example.fox.ratusha.di.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

}