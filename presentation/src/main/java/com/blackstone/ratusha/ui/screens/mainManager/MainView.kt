package com.blackstone.ratusha.ui.screens.mainManager

import com.blackstone.ratusha.ui.base.mvp.BaseView

interface MainView : BaseView {
    fun showProgress()
    fun hideProgress()
}