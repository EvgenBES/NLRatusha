package com.example.fox.ratusha.ui.screens.detailed

import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.RecyclerReceptAdapter
import com.example.fox.ratusha.ui.entity.ItemRecept

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */

class DetailItemPresenter(view: DetailItemView) : BasePresenter<DetailItemRouter, DetailItemView>(view) {

    val adapter = RecyclerReceptAdapter()


    init {
        App.appComponent.runInject(this)

        getItemRecept()
    }

    private fun getItemRecept() {
        val listItem = mutableListOf<ItemRecept>()
        listItem.add(ItemRecept())
        adapter.setItems(listItem)
    }

    private fun setTotal(listRecept: List<ItemRecept>) {
        view.setTotal("23.45")
    }

}