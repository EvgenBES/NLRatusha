package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BaseViewModel

class MainPresenter (var mainView: MainView?) : BaseViewModel() {


    override fun runInject() {
        App.getAppComponent().runInject(this)
    }


    fun onResume() {
        mainView?.showProgress()
    }

    fun onDestroy() {
        mainView = null
    }
}