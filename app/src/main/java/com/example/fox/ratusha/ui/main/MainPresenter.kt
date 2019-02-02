package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter

class MainPresenter(mainView: MainView) : BasePresenter<MainRouter, MainView>(mainView) {

    init {
        App.appComponent.runInject(this)
    }


}