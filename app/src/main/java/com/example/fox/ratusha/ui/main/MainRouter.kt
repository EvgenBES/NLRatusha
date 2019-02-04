package com.example.fox.ratusha.ui.main

import android.widget.Toast
import com.example.fox.ratusha.ui.base.BaseRouter

/**
 * @author Evgeny Butov
 * @since 02.02.2019
 */
class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {

    fun showToastActivity(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}