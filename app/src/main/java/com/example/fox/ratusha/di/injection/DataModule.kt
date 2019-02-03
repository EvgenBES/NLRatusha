package com.example.fox.ratusha.di.injection

import android.content.Context
import com.example.fox.ratusha.data.db.AppDataBase
import com.example.fox.ratusha.data.db.model.forpost.ItemForpDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Evgeny Butov
 * @since 03.02.2019
 */
@Module
class DataModule {

    @Provides
    fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Provides
    fun provideItemForpDao(appDataBase: AppDataBase): ItemForpDao {
        return appDataBase.getForpDao()
    }
}