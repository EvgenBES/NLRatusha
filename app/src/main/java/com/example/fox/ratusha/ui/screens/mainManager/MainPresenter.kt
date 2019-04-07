package com.example.fox.ratusha.ui.screens.mainManager

import com.example.fox.ratusha.data.network.GetOrderAsyncTask
import com.example.fox.ratusha.data.usecases.SetItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.utils.TimerUtils
import javax.inject.Inject


class MainPresenter(view: MainView) : BasePresenter<MainRouter, MainView>(view) {

    var stateRecyclerFragment: Boolean = false

    @Inject
    lateinit var setItemDataBase: SetItemDataBaseUseCase

    init {
        App.appComponent.runInject(this)
        startTimer()
        getOrderInformation()
    }

    fun getOrderInformation() {
        val resultGetOrder = GetOrderAsyncTask().execute().get()

        if (resultGetOrder[0].townHall.start !== " ") {
            setItemDataBase.setOrder(resultGetOrder)
            router?.hideRefreshButtonMainFragment()


        } else {
            router?.showToastActivity("Ошибка соединения...")
            router?.showRefreshButtonMainFragment()
        }
    }

    /**
     * This getting new information from internet
     * once in 5 minutes
     */
    private fun startTimer() {
        val disposable = TimerUtils.observable5m.subscribe(
                { getOrderInformation() },
                { e -> println("MainPresenter startTimer: $e") },
                { }
        )
        addToDisposible(disposable)
    }

}