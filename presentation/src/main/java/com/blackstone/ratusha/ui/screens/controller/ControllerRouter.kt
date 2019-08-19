package com.blackstone.ratusha.ui.screens.controller

import androidx.fragment.app.Fragment
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.BaseRouter
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

    fun startFragmentTest(fragment: Fragment) {
        replaceFragment(
                activity.supportFragmentManager,
                fragment,
                R.id.constraintLayout2,
                true)
    }

    fun refreshInformation() {
        activity.provideViewModel().refreshData()
    }

    fun openSettings() {
        Settings().show(activity.supportFragmentManager, "Settings")
    }
}