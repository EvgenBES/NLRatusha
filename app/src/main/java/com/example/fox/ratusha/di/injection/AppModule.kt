package com.example.fox.ratusha.di.injection


import android.content.Context
import com.example.fox.ratusha.data.repositories.ItemRepository
import com.example.fox.ratusha.data.repositories.ItemRepositoryImpl
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.di.executors.PostExecutionThread
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun provideItemRepository(itemRepository: ItemRepositoryImpl): ItemRepository {
        return itemRepository
    }

    @Provides
    fun providePostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

}