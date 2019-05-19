package com.blackstone.ratusha.ui.screens.controller

import android.support.v4.app.Fragment
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseRouter
import com.blackstone.ratusha.ui.screens.settings.Settings

/**
 * @author Evgeny Butov
 * @created 02.02.2019
 */
class ControllerRouter(activity: ControllerActivity) : BaseRouter<ControllerActivity>(activity) {

    fun startFragment(fragment: Fragment) {
        replaceFragment(
                activity.supportFragmentManager,
                fragment,
                R.id.mainFragment,
                false)
    }

    fun refreshInformation() {
        activity.provideViewModel().refreshData()
    }

    fun openSettings() {
        Settings().show(activity.supportFragmentManager, "Settings")
    }
}