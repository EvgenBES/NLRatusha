package com.example.fox.ratusha.ui.login

class LoginInteractor {

    interface OnLoginFinishedListener {
        fun onUsernameError()
        fun onSuccess()
    }

    fun login (username: String, listener: OnLoginFinishedListener) {
        when {
            username.isEmpty() -> listener.onUsernameError()
            else -> listener.onSuccess()
        }
    }
}