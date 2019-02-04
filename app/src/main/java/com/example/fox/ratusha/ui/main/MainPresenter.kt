package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.data.network.GetOrderAsyncTask
import com.example.fox.ratusha.data.usecases.ItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter(view: MainView) : BasePresenter<MainRouter, MainView>(view) {

    init {
        App.appComponent.runInject(this)
    }

    @Inject
    lateinit var itemDataBase: ItemDataBaseUseCase


    override fun onResume() {
        super.onResume()
        setItem()
    }
    private fun setItem() {
        val resultGetOrder =  GetOrderAsyncTask().execute().get()

        if (resultGetOrder[0].startOrder !== " ") {
            itemDataBase.setOrder(resultGetOrder)
        } else {
            router?.showToastActivity("I'm need internet! Give me please internet!")
        }
    }

}