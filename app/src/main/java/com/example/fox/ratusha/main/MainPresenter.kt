package com.example.fox.ratusha.main

class MainPresenter (var mainView: MainView?) {
    fun onResume() {
        mainView?.showProgress()
    }

    fun onDestroy() {
        mainView = null
    }
}