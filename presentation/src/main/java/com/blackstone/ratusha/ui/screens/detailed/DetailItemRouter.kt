package com.blackstone.ratusha.ui.screens.detailed

import com.blackstone.ratusha.ui.base.mvvm.BaseRouter

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */

class DetailItemRouter(activity: DetailItemInfo) : BaseRouter<DetailItemInfo>(activity){

    fun hideTextCounter() {
        activity.hideTextCount()
    }

    fun showTextCounter() {
        activity.showTextCount()
    }
}