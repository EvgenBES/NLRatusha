package com.blackstone.ratusha.ui.screens.mainManager

import com.blackstone.data.net.GetOrderAsyncTask
import com.blackstone.domain.usecases.SetItemDataBaseUseCase
import com.blackstone.ratusha.di.app.App
import com.blackstone.ratusha.ui.base.mvp.BasePresenter
import com.blackstone.ratusha.utils.TimerUtils
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