package com.blackstone.ratusha.ui.screens.detailed

import com.blackstone.ratusha.ui.base.mvp.BaseView

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */
interface DetailItemView : BaseView {
    fun setItem(itemName: String, itemPrice: Int, itemImage: String, itemReputation: Double, itemCountRep: Int)
}