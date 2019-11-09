package com.blackstone.ratusha.di.injection

import android.content.Context
import com.blackstone.data.db.AppDataBase
import com.blackstone.data.net.RestService
import com.blackstone.data.repositories.ApiRepositoryImpl
import com.blackstone.data.repositories.AppRepositoryImpl
import com.blackstone.data.repositories.DaoRepositoryImpl
import com.blackstone.data.repositories.SharedProviderImpl
import com.blackstone.domain.repositories.ApiRepository
import com.blackstone.domain.repositories.AppRepository
import com.blackstone.domain.repositories.DaoRepository
import com.blackstone.domain.repositories.SharedProvider
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
    fun provideAppDatabase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideApiRepository(serverRepositoryImpl: ApiRepositoryImpl): ApiRepository {
        return serverRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideDaoRepository(daoRepositoryImpl: DaoRepositoryImpl): DaoRepository {
        return daoRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository {
        return appRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideSharedProvider(sharedProviderImpl: SharedProviderImpl): SharedProvider {
        return sharedProviderImpl
    }

    @Singleton
    @Provides
    fun provideRestService(): RestService = RestService()


}