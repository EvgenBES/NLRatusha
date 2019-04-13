package com.blackstone.ratusha.ui.screens.mainManager

import android.support.v4.app.Fragment
import android.widget.Toast
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseRouter
import com.blackstone.ratusha.ui.screens.main.FMain

/**
 * @author Evgeny Butov
 * @created 02.02.2019
 */
class MainRouter(activity: MainActivity) : BaseRouter<MainActivity>(activity) {

    fun showToastActivity(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun startFragment(fragment: Fragment) {
        replaceFragment(
                activity.supportFragmentManager,
                fragment,
                R.id.mainFragment,
                false)
    }

    fun showRefreshButtonMainFragment() {
        val fragment: Fragment? = activity.supportFragmentManager.findFragmentById(R.id.mainFragment)
        if (fragment != null && fragment is FMain) (fragment).showButtonRefresh()
    }

    fun hideRefreshButtonMainFragment() {
        val fragment: Fragment? = activity.supportFragmentManager.findFragmentById(R.id.mainFragment)
        if (fragment != null && fragment is FMain) (fragment).hideButtonRefresh()
    }
}