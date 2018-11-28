package com.example.fox.ratusha.di.injection;


import com.example.fox.ratusha.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void runInject(MainPresenter mainPresenter);
}