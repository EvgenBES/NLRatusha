package com.example.fox.ratusha.ui.screens.mainManager

import android.widget.Toast
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseRouter
import com.example.fox.ratusha.ui.screens.forpost.FForpost

/**
 * @author Evgeny Butov
 * @since 02.02.2019
 */
class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {

    fun showToastActivity(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun startForpostFragment() {
        replaceFragment(
                activity.supportFragmentManager,
                FForpost(),
                R.id.mainFragment,
                false)
    }

    fun startOctalFragment() {

    }

    fun getOrderInformation() {
        activity.providePresenter().getOrderInformation()
    }
}