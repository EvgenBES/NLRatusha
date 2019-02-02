package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.ui.base.BaseView

interface MainView : BaseView{
    fun showProgress()
    fun hideProgress()
    fun setForpostInfo(timeOrder: String, progressOrder: String, urlProduct: String, timeProduct: String)
    fun setOctalInfo(timeOrder: String, progressOrder: String, urlProduct: String, timeProduct: String)
}