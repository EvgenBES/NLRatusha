package com.blackstone.ratusha.ui.screens.main

import android.content.Context
import androidx.databinding.ObservableField
import android.util.Log
import com.blackstone.data.extension.defaultSharedPreferences
import com.blackstone.domain.usecases.GetInfoTownHall
import com.blackstone.domain.usecases.GetItemForpostUseCase
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.R
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils.countProgress
import com.blackstone.ratusha.utils.TimerUtils
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


/**
 * @author Evgeny Butov
 * @created 16.04.2019
 */

class FMainModel : BaseViewModel<ControllerRouter>() {

    companion object {
        const val TAG = "Ratusha FMainModel"
    }

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

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    fun getTimeProduct(): ObservableField<String> = timeProduct

    init {
        App.appComponent.runInject(this)
        getTownHall()
        getProgressOrders()
        startTimer()
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
    private fun getTownHall() {
        val disposable = getInfoTownHall.get().subscribeBy(
            onNext = {
                if (it.size >= 2) {
                    timeOrderForpostNoCast = it[0].finish
                    timeOrderOctalNoCast = it[1].finish
                    urlFirst.set(it[0].url)
                    urlSecond.set(it[1].url)
                }
            },
            onError = { Log.d(TAG, "getTownHall message: ${it.message}") }
        )
        addToDisposable(disposable)
    }

    /**
     * Get all items forpost and octal from database
     */
    private fun getProgressOrders() {
        //Forpost
        addToDisposable(getItemForpost.getAllItemOrder().subscribeBy(
            onNext = { if (it.isNotEmpty()) forpostPercent.set("${countProgress(it)}%") },
            onError = { Log.d(TAG, "getForpostOrders message: ${it.message}") }
        ))

        //Octal
        addToDisposable(getItemOctal.getAllItemOrder().subscribeBy(
            onNext = {
                if (it.isNotEmpty()) octalPercent.set("${countProgress(it)}%")
                updateTime.set(getTimeUpdateSharedPref(router?.activity))
            },
            onError = { Log.d(TAG, "getOctalOrders message: ${it.message}") }
        ))
    }

    private fun startTimer() {
        val disposable = TimerUtils.observable1s.subscribe(
            {
                timeProduct.set(TimerUtils.timerProduct())
                remainderTimeOrderForpost.set(TimerUtils.timeMap(timeOrderForpostNoCast))
                remainderTimeOrderOctal.set(TimerUtils.timeMap(timeOrderOctalNoCast))
                if ((it.toInt() % 60) == 0) updateTime.set(getTimeUpdateSharedPref(router?.activity))
            },
            { e -> println("MainPresenter startTimer: $e") }
        )
        addToDisposable(disposable)
    }

    private fun getTimeUpdateSharedPref(context: Context?): String {
        if (context == null) return ""

        val sharedPref = context.defaultSharedPreferences
        val result = sharedPref.getLong(ControllerModel.UPDATE_TIME, 0L)
        val time = TimerUtils.updateTime(result)

        return if (time < 60) context.getString(R.string.update_data_less60min, time) else context.getString(R.string.update_data_more60min)
    }
}