package com.example.fox.ratusha.di.injection


import android.content.Context
import com.example.fox.ratusha.data.db.ItemDataBase
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
    fun provideUIThread(): PostExecutionThread = UIThread()

    @Provides
    fun provideUserDataBase(context: Context): ItemDataBase {
        return ItemDataBase.getInstance(context)
    }

    @Provides
    fun provideCoinRepository(itemRepository: ItemRepositoryImpl): ItemRepository {
        return itemRepository
    }

}