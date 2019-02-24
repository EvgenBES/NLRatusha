package com.example.fox.ratusha.ui.screens.main

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetInfoTownHall
import com.example.fox.ratusha.data.usecases.GetItemForpostUseCase
import com.example.fox.ratusha.data.usecases.GetItemOctalUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FMainPresenter(view: FMainView) : BasePresenter<MainRouter, FMainView>(view) {

    private lateinit var timerThread: Thread
    private var remainderTimeOrderForpost = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L) //today + 10days
    private var remainderTimeOrderOctal = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date().time + 864000000L)

    init {
        App.appComponent.runInject(this)
        getTownHall()
        getProgressOrders()
    }

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    override fun onResume() {
        super.onResume()
        timerThread = createdTimerThread()
        startTimerThread()
    }

    override fun onPause() {
        super.onPause()
        stopTimerThread()
    }

    private fun startTimerThread() {
        if (!timerThread.isAlive) timerThread.start()
    }

    private fun stopTimerThread() {
        timerThread.interrupt()
    }

    private fun setTimeOrder() {
        view.setForpostTime(timeMap(remainderTimeOrderForpost))
        view.setOctalTime(timeMap(remainderTimeOrderOctal))
    }

    /**
     * This method map remainder time orders
     * We gain param finish String orders and map him to remainder time orders
     * For example: we gain
     * @param finish its String "08.02.2019 03:46"
     * @return result where String finish minus real time = remainder time orders "4д 10:25:17"
     */
    private fun timeMap(finish: String): String {
        return if (finish != " ") {
            val fixTime = 10800000L // 3 hor
            val dateFinish = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(finish).time
            val dateNew = dateFinish - Date().time - fixTime

            val daysLeft = SimpleDateFormat("d", Locale.getDefault()).format(dateNew).toInt()
            val result = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(dateNew)

            "${daysLeft - 1}д $result"
        } else {
            "0д 0:00:00"
        }
    }

    private fun countProgress(listItem: List<ItemOrder>): String {
        var sumStartCount = 0.0
        var sumFinishCount = 0.0

        for (item in listItem) {
            sumStartCount += item.countStart
            sumFinishCount += item.countFinish
        }

        val result = (sumStartCount / (sumFinishCount / 100))

        return "${result.toInt()}%" // return 0..99%
    }

    /**
     * This thread refresh times to textview - interval 1s
     */
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
                    Log.e("MainPresenter", "InterruptedException ${e.message}")
                }
            }
        }
    }

    /**
     * Here we create remains time - product
     * @param hour - we get hour and %2 = 0 || 1
     * @param minute - we get time in format (min:sec)
     * @param getTimeLong - we transform minute to long
     * @param fixedTimeLong - we get long 2hour
     *
     * @return result: 2 hour minus current time = time CD new products
     */
    private fun timerProduct() {
        val hour = SimpleDateFormat("HH").format(Date()).toInt() % 2
        val minute: String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(Date())
        val getTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse(minute).time
        val fixedTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse("59:59").time

        val result: String = SimpleDateFormat("mm:ss", Locale.getDefault()).format(fixedTimeLong - getTimeLong)

        view.setTimeProduct("0${if (hour == 0) 1 else 0}:$result")
    }

    private fun setImageProduct(urlImageProductForpost: String, urlImageProductOctal: String) {
        view.setImageProductForpost(urlImageProductForpost)
        view.setImageProductOctal(urlImageProductOctal)
    }

    /**
     * Get remainder time and get images product orders from database
     */
    private fun getTownHall() {
        val disposable = getInfoTownHall.get().subscribeBy(
                onNext = {
                    if (it.size >= 2) {
                        remainderTimeOrderForpost = it[0].finish
                        remainderTimeOrderOctal = it[1].finish
                        setImageProduct(it[0].url, it[1].url)
                    }
                },
                onError = { Log.d("AAQQ", "getTownHall message: ${it.message}") }
        )
        addToDisposible(disposable)
    }

    /**
     * Get all items forpost and octal from database
     */
    private fun getProgressOrders() {
        //Forpost
        val disposable = getItemForpost.getAllItemOrder().subscribeBy(
                onNext = { if (it.isNotEmpty()) view.setForpostProgress(countProgress(it)) },
                onError = { Log.d("AAQQ", "getForpostOrders message: ${it.message}") }
        )
        addToDisposible(disposable)

        //Octal
        val disposableOct = getItemOctal.getAllItemOrder().subscribeBy(
                onNext = { if (it.isNotEmpty()) view.setOctalProgress(countProgress(it)) },
                onError = { Log.d("AAQQ", "getOctalOrders message: ${it.message}") }
        )
        addToDisposible(disposableOct)
    }
}