package com.blackstone.ratusha.ui.screens.forpost

import com.blackstone.ratusha.ui.base.mvp.BaseView
import com.blackstone.domain.entity.TotalSum

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
interface FForpostView : BaseView {
    fun setTimerOrder(time: String)
    fun setTotalSum(totalSum: TotalSum)
}