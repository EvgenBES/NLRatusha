package com.blackstone.ratusha.ui.screens.main

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.MetaInfo
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.usecases.townhall.GetInfoTownHallUseCase
import com.blackstone.domain.usecases.forpost.GetItemForpostUseCase
import com.blackstone.domain.usecases.octal.GetItemOctalUseCase
import com.blackstone.domain.usecases.metadata.GetMetadataUseCase
import com.blackstone.ratusha.R
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils.countProgress
import com.blackstone.ratusha.utils.DisplayUtils.getDensityDouble
import com.blackstone.ratusha.utils.TimerUtils
import javax.inject.Inject


/**
 * @author Evgeny Butov
 * @created 16.04.2019
 */

class FMainModel : BaseViewModel<ControllerRouter>() {

    private val productTime = ObservableField<String>()
    private val updateTime = ObservableField<String>()
    private val remainderTimeOrderForpost = ObservableField<String>()
    private val remainderTimeOrderOctal = ObservableField<String>()
    private val forpostPercent = ObservableField<String>()
    private val octalPercent = ObservableField<String>()
    private val productForpost: ObservableField<String> = ObservableField<String>()
    private val productOctal: ObservableField<String> = ObservableField<String>()
    private val progressForpost: ObservableInt = ObservableInt()
    private val progressOctal: ObservableInt = ObservableInt()
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

    init {
        App.appComponent.runInject(this)
        getItemForpost.getAllItemOrder().observeForever(itemsOrederForpost)
        getItemOctal.getAllItemOrder().observeForever(itemsOrederOctal)
        getInfoTownHall.get().observeForever(listTownHall)
        getMetadata.get().observeForever(metaObserver)
        startTimer()
    }

    override fun onCleared() {
        getItemForpost.getAllItemOrder().removeObserver(itemsOrederForpost)
        getItemOctal.getAllItemOrder().removeObserver(itemsOrederOctal)
        getInfoTownHall.get().removeObserver(listTownHall)
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
    fun getProductTime(): ObservableField<String> = productTime
    fun getProgressForpost(): ObservableInt = progressForpost
    fun getProgressOctal(): ObservableInt = progressOctal

    /**
     * Get remainder time and get images product orders from database
     */
    private fun setTownHall(list: List<TownHall>) {
        if (list.size >= 2) {
            timeOrderForpostNoCast = list[0].finish
            timeOrderOctalNoCast = list[1].finish
            productForpost.set(list[0].url.replace(".gif", ""))
            productOctal.set(list[1].url.replace(".gif", ""))
        }
    }

    /**
     * Get all items forpost and octal from database
     */
    private fun setProgressOrdersForpost(list: List<ItemOrder>) {
        val forProgress = countProgress(list)
        forpostPercent.set("${forProgress}%")

        router?.activity?.let {
            val progressInt = (Integer.valueOf(forProgress) * 1.45 * getDensityDouble(it.applicationContext)).toInt()
            progressForpost.set(progressInt)
        }
    }

    private fun setProgressOrdersOctal(list: List<ItemOrder>) {
        val octProgress = countProgress(list)
        octalPercent.set("${octProgress}%")

        router?.activity?.let {
            val progressInt = (Integer.valueOf(octProgress) * 1.45 * getDensityDouble(it.applicationContext)).toInt()
            progressOctal.set(progressInt)
        }
    }

    private fun startTimer() {
        TimerUtils.repeatAfter1Sec {
            productTime.set(TimerUtils.timerProduct())
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