package com.blackstone.ratusha.ui.screens.main

import com.blackstone.ratusha.ui.base.mvp.BaseView

/**
 * @author Evgeny Butov
 * @created 16.02.2019
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