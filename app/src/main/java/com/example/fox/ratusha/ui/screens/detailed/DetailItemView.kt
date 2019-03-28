package com.example.fox.ratusha.ui.screens.detailed

import com.example.fox.ratusha.ui.base.BaseView

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */
interface DetailItemView : BaseView {
    fun setTotal(totalCost: String)
    fun setItem(itemName: String, itemPrice: Int, itemImage: String, itemReputation: Double, itemCountRep: Int)
}