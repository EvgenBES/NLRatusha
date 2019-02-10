package com.example.fox.ratusha.ui.screens.main

import android.util.Log
import com.example.fox.ratusha.data.network.GetOrderAsyncTask
import com.example.fox.ratusha.data.usecases.GetInfoTownHall
import com.example.fox.ratusha.data.usecases.GetItemForpostUseCase
import com.example.fox.ratusha.data.usecases.GetItemOctalUseCase
import com.example.fox.ratusha.data.usecases.SetItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.entity.ItemOrder
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class MainPresenter(view: MainView) : BasePresenter<MainRouter, MainView>(view) {

    private lateinit var timerThread: Thread
    private lateinit var getOrderInfoThread: Thread
    private var remainderTimeOrderForpost = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L) //today + 10days
    private var remainderTimeOrderOctal = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L)

    @Inject
    lateinit var itemDataBase: SetItemDataBaseUseCase

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    init {
        App.appComponent.runInject(this)

        getTownHall()
        getProgressOrders()
    }

    override fun onResume() {
        super.onResume()

        timerThread = createdTimerThread()
        startTimerThread()

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
            itemDataBase.setOrder(resultGetOrder)
            router?.activity?.hideButtonRefresh()

            if (!getOrderInfoThread.isAlive) {
                getOrderInfoThread.start()
            }

        } else {
            router?.showToastActivity("Ошибка соединения...")
            router?.activity?.visibleButtonRefresh()

            if (getOrderInfoThread.isAlive) {
                getOrderInfoThread.interrupt()
            }
        }
    }

    private fun setProgressOrder(listItemForpost: List<ItemOrder>, listItemOctal: List<ItemOrder>) {
        router?.activity?.setForpostProgress(countProgress(listItemForpost))
        router?.activity?.setOctalProgress(countProgress(listItemOctal))
    }

    private fun setImageProduct(urlImageProductForpost: String, urlImageProductOctal: String) {
        router?.activity?.setImageProductForpost(urlImageProductForpost)
        router?.activity?.setImageProductOctal(urlImageProductOctal)
    }

    /**
     * This method map remainder time orders
     * We gain param finish String orders and map him to remainder time orders
     * For example: we gain
     * @param finish its String "08.02.2019 03:46"
     * @return result where String finish minus real time = remainder time orders "4д 10:25:17"
     */
    private fun timeMap(finish: String): String {
        val fixTime = 10800000L // 3 hor
        val dateFinish = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(finish).time
        val dateNew = dateFinish - Date().time - fixTime

        val daysLeft = SimpleDateFormat("d", Locale.getDefault()).format(dateNew).toInt()
        val result = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(dateNew)

        return "${daysLeft - 1}д $result"
    }

    private fun countProgress(listItem: List<ItemOrder>): String {
        var sumStartCount = 0.0
        var sumFinishCount = 0.0

        for (item in listItem) {
            sumStartCount += item.countStart
            sumFinishCount += item.countFinish
        }

        val result: Double = (100 - ((sumFinishCount / sumStartCount) * 10))

        return "${result.toInt()}%" // return 0..99%
    }


    private fun timerProduct() {
        val hour = SimpleDateFormat("HH").format(Date()).toInt() % 2
        val minute = SimpleDateFormat("mm:ss", Locale.getDefault()).format(Date())
        val getTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse(minute).time
        val fixedTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse("59:59").time

        val result = SimpleDateFormat("mm:ss", Locale.getDefault()).format(fixedTimeLong - getTimeLong)

        router?.activity?.setTimeProduct("0${if (hour == 0) 1 else 0}:$result")
    }

    private fun setTimeOrder() {
        router?.activity?.setForpostTime(timeMap(remainderTimeOrderForpost))
        router?.activity?.setOctalTime(timeMap(remainderTimeOrderOctal))
    }

    private fun startTimerThread() {
        if (!timerThread.isAlive) {
            timerThread.start()
        }
    }

    private fun stopTimerThread() {
        timerThread.interrupt()

        if (getOrderInfoThread.isAlive) {
            getOrderInfoThread.interrupt()
        }
    }

    private fun createdTimerThread(): Thread {
        return object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        router?.activity?.runOnUiThread {
                            timerProduct()
                            setTimeOrder()
                        }
                        sleep(1000) //1s
                    }
                } catch (e: InterruptedException) {
//                    Log.e("MainPresenter", "InterruptedException ${e.message}")
                }
            }
        }
    }


    private fun createdRefreshInfoThread(): Thread {
        return object : Thread() {
            override fun run() {
                try {
                    while (!isInterrupted) {
                        router?.activity?.runOnUiThread { getOrderInformation() }
                        sleep(5 * 2 * 1000) // 5m * 60s * 1k ms
                    }
                } catch (e: InterruptedException) {
//                    Log.e("MainPresenter", "InterruptedException ${e.message}")
                }
            }
        }
    }


    private fun getTownHall() {
        val disposable = getInfoTownHall.get().subscribeBy(
                onNext = {
                    if (it.size >= 2) {
                        remainderTimeOrderForpost = it[0].finish
                        remainderTimeOrderOctal = it[1].finish
                        setImageProduct(it[0].url, it[1].url)
                    }
                },
                onError = { Log.d("AAQQ", "message: ${it.message}") }
        )
        addToDisposible(disposable)
    }

    private fun getProgressOrders() {
        val disposable = getItemForpost.getAllItemOrder().subscribeBy(
                onNext = { router?.activity?.setForpostProgress(countProgress(it)) },
                onError = { Log.d("AAQQ", "message: ${it.message}") }
        )
        addToDisposible(disposable)

        val disposableOct = getItemOctal.getAllItemOrder().subscribeBy(
                onNext = { router?.activity?.setOctalProgress(countProgress(it)) },
                onError = { Log.d("AAQQ", "message: ${it.message}") }
        )
        addToDisposible(disposableOct)
    }


}