package com.blackstone.ratusha.di.injection

import android.content.Context
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.net.RestService
import com.blackstone.data.repositories.DaoRepositoryImpl
import com.blackstone.data.repositories.ServerRepositoryImpl
import com.blackstone.domain.executors.PostExecutionThread
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.repositories.ServerRepository
import com.blackstone.notif.NotificationR
import com.blackstone.ratusha.di.executors.UIThread
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
    fun provideItemRepository(itemRepository: ServerRepositoryImpl): ServerRepository {
        return itemRepository
    }

    @Singleton
    @Provides
    fun provideItemDaoRepository(itemDaoRepository: DaoRepositoryImpl): DaoRepository {
        return itemDaoRepository
    }

    @Singleton
    @Provides
    fun provideNotificationR(appDataBase: AppDataBase, context: Context): NotificationR {
        return NotificationR(appDataBase, context)
    }

    @Singleton
    @Provides
    fun providePostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

    @Singleton
    @Provides
    fun provideRestService(): RestService = RestService()


}