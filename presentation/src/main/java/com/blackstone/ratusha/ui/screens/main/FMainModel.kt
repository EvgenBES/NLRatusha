package com.blackstone.ratusha.ui.screens.main

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.blackstone.data.extension.defaultSharedPreferences
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.usecases.GetInfoTownHall
import com.blackstone.domain.usecases.GetItemForpostUseCase
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.R
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerModel
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

    val urlFirst = ObservableField<String>()
    val urlSecond = ObservableField<String>()

    private var timeOrderForpostNoCast: String = TimerUtils.getDefTimerOrder()
    private var timeOrderOctalNoCast: String = TimerUtils.getDefTimerOrder()

    private val listTownHall: Observer<List<TownHall>> = Observer { list -> setTownHall(list) }
    private val itemsOrederForpost: Observer<List<ItemOrder>> = Observer { list -> setProgressOrdersForpost(list) }
    private val itemsOrederOctal: Observer<List<ItemOrder>> = Observer { list -> setProgressOrdersOctal(list) }

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    fun getTimeProduct(): ObservableField<String> = timeProduct

    init {
        App.appComponent.runInject(this)
        getInfoTownHall.get().observeForever(listTownHall)
        getItemForpost.getAllItemOrder().observeForever(itemsOrederForpost)
        getItemOctal.getAllItemOrder().observeForever(itemsOrederOctal)
        startTimer()
    }

    override fun onCleared() {
        getInfoTownHall.get().removeObserver(listTownHall)
        getItemForpost.getAllItemOrder().removeObserver(itemsOrederForpost)
        getItemOctal.getAllItemOrder().removeObserver(itemsOrederOctal)
        super.onCleared()
    }

    override fun onResume() {
        updateTime.set(getTimeUpdateSharedPref(router?.activity))
    }

    fun getUpdateTime(): ObservableField<String> = updateTime
    fun getTimeOrderForpost(): ObservableField<String> = remainderTimeOrderForpost
    fun getTimeOrderOctal(): ObservableField<String> =  remainderTimeOrderOctal
    fun getForpostPercent(): ObservableField<String> = forpostPercent
    fun getOctalPercent(): ObservableField<String> = octalPercent

    /**
     * Get remainder time and get images product orders from database
     */
    private fun setTownHall(list: List<TownHall>) {
        if (list.size >= 2) {
            timeOrderForpostNoCast = list[0].finish
            timeOrderOctalNoCast = list[1].finish
            urlFirst.set(list[0].url)
            urlSecond.set(list[1].url)
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

        TimerUtils.repeatAfter1Sec(6000L) {
            updateTime.set(getTimeUpdateSharedPref(router?.activity))
        }
    }

    private fun getTimeUpdateSharedPref(context: Context?): String {
        if (context == null) return ""

        val sharedPref = context.defaultSharedPreferences
        val result = sharedPref.getLong(ControllerModel.UPDATE_TIME, 0L)
        val time = TimerUtils.updateTime(result)

        return if (time < 60) context.getString(R.string.update_data_less60min, time) else context.getString(R.string.update_data_more60min)
    }
}