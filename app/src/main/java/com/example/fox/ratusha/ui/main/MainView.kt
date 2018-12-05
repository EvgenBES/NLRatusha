package com.example.fox.ratusha.ui.main

interface MainView {
    fun showProgress()
    fun hideProgress()
    fun setForpostInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String)
    fun setOctalInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String)
}