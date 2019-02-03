package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.data.usecases.ItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainRouter, MainView>(mainView) {

    init {
        App.appComponent.runInject(this)
    }

    @Inject
    lateinit var itemDataBase: ItemDataBaseUseCase

    fun setItem() {

        val list: List<ItemOrder> = arrayListOf(ItemOrder("1", "2", "3", "4", "5"))
        val order: List<Order> = arrayListOf(Order("1", "2", list, "3", "4"))

        itemDataBase.setOrder(order)
    }

}