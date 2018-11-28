package com.example.fox.ratusha.di.injection;


import android.content.Context;

import com.example.fox.ratusha.data.db.ItemDataBase;
import com.example.fox.ratusha.data.repositories.ItemRepository;
import com.example.fox.ratusha.data.repositories.ItemRepositoryImpl;
import com.example.fox.ratusha.di.executors.PostExecutionThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }


    @Provides
    public static ItemRepository provideCoinRepository(ItemRepositoryImpl itemRepository) {
        return itemRepository;
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }

    @Singleton
    @Provides
    public static ItemDataBase provideUserDataBase(Context context) {
        return ItemDataBase.getInstance(context);
    }



}
