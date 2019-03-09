package com.example.fox.ratusha.di.injection

import android.content.Context
import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.repositories.RatushaRepository
import com.example.fox.ratusha.data.repositories.RatushaRepositoryImpl
import com.example.fox.ratusha.di.executors.PostExecutionThread
import com.example.fox.ratusha.di.executors.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Evgeny Butov
 * @created 03.02.2019
 */
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideItemRepository(itemRepository: RatushaRepositoryImpl): RatushaRepository {
        return itemRepository
    }

    @Singleton
    @Provides
    fun providePostExecutionThread(): PostExecutionThread {
        return UIThread()
    }


//    @Singleton
//    @Provides
//    fun provideUIThread(uiThread: UIThread): PostExecutionThread {
//        return uiThread
//    }


//    @Provides
//    fun provideItemForpDao(appDataBase: AppDataBase): ItemForpDao {
//        return appDataBase.getForpDao()
//    }
}