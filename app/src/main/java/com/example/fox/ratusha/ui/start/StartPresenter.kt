package com.example.fox.ratusha.ui.start

class StartPresenter(var loginView: StartView?){
    fun onDestroy() {
        loginView = null
    }
}