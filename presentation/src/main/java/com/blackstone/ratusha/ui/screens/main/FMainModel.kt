package com.blackstone.ratusha.ui.screens.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.MetaInfo
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.usecases.GetInfoTownHallUseCase
import com.blackstone.domain.usecases.GetItemForpostUseCase
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.domain.usecases.GetMetadataUseCase
import com.blackstone.ratusha.R
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils.countProgress
import com.blackstone.ratusha.utils.TimerUtils
import javax.inject.Inject


/**
 * @author Evgeny Butov
 * @created 16.04.2019
 */

class FMainModel : BaseViewModel<ControllerRouter>() {

    private val timeProduct = ObservableField<String>("02:00:00")
    private val updateTime = ObservableField<String>()
    private val remainderTimeOrderForpost = ObservableField<String>("9д 23:59:59")
    private val remainderTimeOrderOctal = ObservableField<String>("9д 23:59:59")
    private val forpostPercent = ObservableField<String>("00%")
    private val octalPercent = ObservableField<String>("00%")
    private val productForpost = ObservableField<String>()
    private val productOctal = ObservableField<String>()
    private var updateOrder: Long = 0

    private var timeOrderForpostNoCast: String = TimerUtils.getDefTimerOrder()
    private var timeOrderOctalNoCast: String = TimerUtils.getDefTimerOrder()

    private val listTownHall: Observer<List<TownHall>> = Observer { list -> setTownHall(list) }
    private val itemsOrederForpost: Observer<List<ItemOrder>> = Observer { list -> setProgressOrdersForpost(list) }
    private val itemsOrederOctal: Observer<List<ItemOrder>> = Observer { list -> setProgressOrdersOctal(list) }
    private val metaObserver: Observer<MetaInfo> = Observer { data -> updateTimeOrder(data?.updateDate?.time) }

    @Inject lateinit var getInfoTownHall: GetInfoTownHallUseCase
    @Inject lateinit var getItemForpost: GetItemForpostUseCase
    @Inject lateinit var getItemOctal: GetItemOctalUseCase
    @Inject lateinit var getMetadata: GetMetadataUseCase

    fun getTimeProduct(): ObservableField<String> = timeProduct

    init {
        App.appComponent.runInject(this)
        getInfoTownHall.get().observeForever(listTownHall)
        getItemForpost.getAllItemOrder().observeForever(itemsOrederForpost)
        getItemOctal.getAllItemOrder().observeForever(itemsOrederOctal)
        getMetadata.get().observeForever(metaObserver)
        startTimer()
    }

    override fun onCleared() {
        getInfoTownHall.get().removeObserver(listTownHall)
        getItemForpost.getAllItemOrder().removeObserver(itemsOrederForpost)
        getItemOctal.getAllItemOrder().removeObserver(itemsOrederOctal)
        getMetadata.get().removeObserver(metaObserver)
        super.onCleared()
    }

    override fun onResume() {
       updateTimeOrder(updateOrder)
    }

    fun getUpdateTime(): ObservableField<String> = updateTime
    fun getTimeOrderForpost(): ObservableField<String> = remainderTimeOrderForpost
    fun getTimeOrderOctal(): ObservableField<String> =  remainderTimeOrderOctal
    fun getForpostPercent(): ObservableField<String> = forpostPercent
    fun getOctalPercent(): ObservableField<String> = octalPercent
    fun getProductForpost(): ObservableField<String> = productForpost
    fun getProductOctal(): ObservableField<String> = productOctal

    /**
     * Get remainder time and get images product orders from database
     */
    private fun setTownHall(list: List<TownHall>) {
        if (list.size >= 2) {
            timeOrderForpostNoCast = list[0].finish
            timeOrderOctalNoCast = list[1].finish
            productForpost.set(list[0].url)
            productOctal.set(list[1].url)
        }
    }

    /**
     * Get all items forpost and octal from database
     */
    private fun setProgressOrdersForpost(list: List<ItemOrder>) {
        forpostPercent.set("${countProgress(list)}%")
    }

    private fun setProgressOrdersOctal(list: List<ItemOrder>) {
        octalPercent.set("${countProgress(list)}%")
    }

    private fun startTimer() {
        TimerUtils.repeatAfter1Sec {
            timeProduct.set(TimerUtils.timerProduct())
            remainderTimeOrderForpost.set(TimerUtils.timeMap(timeOrderForpostNoCast))
            remainderTimeOrderOctal.set(TimerUtils.timeMap(timeOrderOctalNoCast))
        }

        TimerUtils.repeatAfter1Sec(60_000L) {
           updateTimeOrder(updateOrder)
        }
    }

    private fun updateTimeOrder(data: Long?) {
        var time: Int = 60
        data?.let {
            updateOrder = it
            time = TimerUtils.updateTime(data)
        }
        val timeString = if (time < 60) router?.activity?.getString(R.string.update_data_less60min, time) else router?.activity?.getString(R.string.update_data_more60min)

        updateTime.set(timeString ?: "")
    }
}