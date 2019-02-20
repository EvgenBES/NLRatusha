package com.example.fox.ratusha.ui.screens.mainManager

import android.util.Log
import com.example.fox.ratusha.data.network.GetOrderAsyncTask
import com.example.fox.ratusha.data.usecases.SetItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import javax.inject.Inject


class MainPresenter(view: MainView) : BasePresenter<MainRouter, MainView>(view) {

    private lateinit var getOrderInfoThread: Thread

    @Inject
    lateinit var setItemDataBase: SetItemDataBaseUseCase

    init {
        App.appComponent.runInject(this)
    }

    override fun onResume() {
        super.onResume()
        getOrderInfoThread = createdRefreshInfoThread()
        getOrderInformation()
    }

    override fun onPause() {
        super.onPause()
        stopTimerThread()
    }

    fun getOrderInformation() {
        val resultGetOrder = GetOrderAsyncTask().execute().get()

        if (resultGetOrder[0].townHall.start !== " ") {
            setItemDataBase.setOrder(resultGetOrder)
            router?.hideRefreshButtonMainFragment()

            if (!getOrderInfoThread.isAlive) {
                getOrderInfoThread.start()
            }

        } else {
            router?.showToastActivity("Ошибка соединения...")
            router?.showRefreshButtonMainFragment()

            if (getOrderInfoThread.isAlive) {
                getOrderInfoThread.interrupt()
            }
        }
    }

    private fun stopTimerThread() {
        if (getOrderInfoThread.isAlive) getOrderInfoThread.interrupt()
    }

    /**
     * This thread getting new information from internet
     * once in 5 minutes
     */
    private fun createdRefreshInfoThread(): Thread {
        return object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        router?.activity?.runOnUiThread { getOrderInformation() }
                        sleep(5 * 5 * 1000) // 5m * 60s * 1k ms
                    }
                } catch (e: InterruptedException) {
                    Log.e("MainPresenter", "InterruptedException ${e.message}")
                }
            }
        }
    }

}