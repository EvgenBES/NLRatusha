package com.blackstone.ratusha.ui.screens.controller

import androidx.fragment.app.Fragment
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.BaseRouter
import com.blackstone.ratusha.ui.screens.settings.Settings
import com.blackstone.ratusha.ui.screens.settings.Settings.Companion.SETTING

/**
 * @author Evgeny Butov
 * @created 02.02.2019
 */
class ControllerRouter(activity: ControllerActivity) : BaseRouter<ControllerActivity>(activity) {

    fun startReplaceFragment(fragment: Fragment, tag: String) {
        replaceFragment(
            activity.supportFragmentManager,
            fragment,
            tag,
            R.id.detailFragment,
            true
        )
    }

    fun refreshInformation() {
        activity.provideViewModel().refreshData()
    }

    fun openSettings() {
        Settings().show(activity.supportFragmentManager, SETTING)
    }
}