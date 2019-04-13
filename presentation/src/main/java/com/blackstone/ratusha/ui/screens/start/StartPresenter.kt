package com.blackstone.ratusha.ui.screens.start

class StartPresenter(var loginView: StartView?){
    fun onDestroy() {
        loginView = null
    }
}