package com.example.fox.ratusha.ui.screens.main

import com.example.fox.ratusha.ui.base.BaseView

interface MainView : BaseView{
    fun showProgress()
    fun hideProgress()
    fun setImageProductForpost(urlProduct: String)
    fun setImageProductOctal(urlProduct: String)
}