package com.blackstone.ratusha.di.injection

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.net.RestService
import com.blackstone.data.repositories.DaoRepositoryImpl
import com.blackstone.data.repositories.ServerRepositoryImpl
import com.blackstone.device.notification.NotificationBuilder
import com.blackstone.device.notification.Notifications
import com.blackstone.device.notification.NotificationsImpl
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.repositories.ServerRepository
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
    fun provideItemRepository(serverRepositoryImpl: ServerRepositoryImpl): ServerRepository {
        return serverRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideItemDaoRepository(daoRepositoryImpl: DaoRepositoryImpl): DaoRepository {
        return daoRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideRestService(): RestService = RestService()


}