package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.ui.base.BaseView

interface MainView : BaseView{
    fun showProgress()
    fun hideProgress()
    fun setForpostInfo(progressOrder: String, urlProduct: String)
    fun setOctalInfo(progressOrder: String, urlProduct: String)
}