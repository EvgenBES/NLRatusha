package com.example.fox.ratusha.ui.screens.forpost

import com.example.fox.ratusha.ui.base.BaseView
import com.example.fox.ratusha.ui.entity.TotalSum

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
interface FForpostView : BaseView {
    fun setTimerOrder(time: String)
    fun setTotalSum(totalSum: TotalSum)
}