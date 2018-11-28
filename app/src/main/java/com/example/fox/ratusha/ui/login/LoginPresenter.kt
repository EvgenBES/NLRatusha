package com.example.fox.ratusha.ui.login

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
        LoginInteractor.OnLoginFinishedListener {

    fun validateCredentials(username: String){
        loginInteractor.login(username, this)
    }

    fun onDestroy() {
        loginView = null
    }


    override fun onUsernameError() {
       loginView?.apply {
           setUsernameError()
       }
    }

    override fun onSuccess() {

        loginView?.navigateToHome()
    }

}