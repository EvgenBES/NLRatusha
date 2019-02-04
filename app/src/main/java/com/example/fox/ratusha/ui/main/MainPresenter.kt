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
            router?.showToastActivity("I'm need internet! Give me please internet!")
        }
    }

    private fun setViewItem(resultGetOrder: List<Order>) {

        router?.activity?.setForpostInfo(timeMap(resultGetOrder[0].startOrder, resultGetOrder[0].finishOrder),
                countProgress(resultGetOrder[0].listItem), resultGetOrder[0].urlProduct, "01:32:32")

        router?.activity?.setOctalInfo(timeMap(resultGetOrder[1].startOrder, resultGetOrder[1].finishOrder),
                countProgress(resultGetOrder[1].listItem), resultGetOrder[1].urlProduct, "00:02:12")
    }


    private fun timeMap(start: String, finish: String): String {
        val dateStart = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(start).time
        val dateFinish = SimpleDateFormat("dd.MM.yyyy HH:mm").parse(finish).time

        val currentDate = Date()
        val dateNew = currentDate.time - (dateFinish - dateStart)

        val day = SimpleDateFormat("d").format(dateNew)
        val time = SimpleDateFormat("HH:mm:ss").format(dateNew)

        return "${day}д $time"
    }

    private fun countProgress(listItem: List<ItemOrder>): String {
        var sumStartCount = 0.0
        var sumFinishCount = 0.0

        for (item in listItem) {
            sumStartCount += item.countStart.toInt()
            sumFinishCount += item.countFinish.toInt()
        }

        val result: Double = (100 - ((sumFinishCount / sumStartCount) * 10))

        return "${result.toInt()}%"
    }

}