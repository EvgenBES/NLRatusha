package com.example.fox.ratusha.ui.main

import com.example.fox.ratusha.data.network.GetOrderAsyncTask
import com.example.fox.ratusha.data.usecases.ItemDataBaseUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.entity.Order
import java.text.SimpleDateFormat
import java.util.*
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

    fun setItem() {
        val resultGetOrder = GetOrderAsyncTask().execute().get()

        if (resultGetOrder[0].startOrder !== " ") {
            itemDataBase.setOrder(resultGetOrder)
            setViewItem(resultGetOrder)
        } else {
            router?.showToastActivity("Ошибка соединения...")
        }

        timerProduct()
    }

    private fun setViewItem(resultGetOrder: List<Order>) {

        router?.activity?.setForpostInfo(timeMap(resultGetOrder[0].finishOrder),
                countProgress(resultGetOrder[0].listItem), resultGetOrder[0].urlProduct)

        router?.activity?.setOctalInfo(timeMap(resultGetOrder[1].finishOrder),
                countProgress(resultGetOrder[1].listItem),
                resultGetOrder[1].urlProduct)
    }


    private fun timeMap(finish: String): String {
        val fixTime = 10800000L // 3 hor
        val dateFinish = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(finish).time
        val dateNew = dateFinish - Date().time - fixTime

        val daysLeft = SimpleDateFormat("d", Locale.getDefault()).format(dateNew).toInt()
        val result = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(dateNew)

        return "${daysLeft - 1}д $result" //return 4д 12:03:01
    }

    private fun countProgress(listItem: List<ItemOrder>): String {
        var sumStartCount = 0.0
        var sumFinishCount = 0.0

        for (item in listItem) {
            sumStartCount += item.countStart.toInt()
            sumFinishCount += item.countFinish.toInt()
        }

        val result: Double = (100 - ((sumFinishCount / sumStartCount) * 10))

        return "${result.toInt()}%" // return 0..99%
    }

    private fun timerProduct() {
        val hour = SimpleDateFormat("HH").format(Date()).toInt() % 2
        val minute = SimpleDateFormat("mm:ss", Locale.getDefault()).format(Date())
        val getTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse(minute).time
        val fixedTimeLong = SimpleDateFormat("mm:ss", Locale.getDefault()).parse("00:00").time

        val result = SimpleDateFormat("mm:ss", Locale.getDefault()).format(fixedTimeLong - getTimeLong)

        router?.activity?.setTimeProduct("0${if (hour == 0) 1 else 0}:$result")
    }


}


