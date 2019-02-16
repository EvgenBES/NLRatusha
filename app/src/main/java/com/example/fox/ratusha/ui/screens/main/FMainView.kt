package com.example.fox.ratusha.ui.screens.main

import com.example.fox.ratusha.ui.base.BaseView

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
interface FMainView: BaseView {
    fun setImageProductForpost(urlProduct: String)
    fun setImageProductOctal(urlProduct: String)
    fun setForpostProgress(progress: String)
    fun setOctalProgress(progress: String)
    fun setForpostTime(time: String)
    fun setOctalTime(time: String)
    fun setTimeProduct(time: String)
}