package com.example.fox.ratusha.ui.base

import android.widget.Toast

abstract class BaseRouter<A : BaseActivity>(val activity: A) {

    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}