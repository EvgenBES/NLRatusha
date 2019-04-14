package com.blackstone.ratusha.ui.screens.controller

import com.blackstone.domain.usecases.UpdateDataUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.utils.TimerUtils
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
class ControllerViewModel : BaseViewModel<ControllerRouter>() {

    var stateRecyclerFragment: Boolean = false

    @Inject
    lateinit var updateDataBase: UpdateDataUseCase

    init {
        App.appComponent.runInject(this)
        getOrderInformation()
        startTimer()
    }

    fun getOrderInformation() {
        updateDataBase.updateData()
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
        addToDisposable(disposable)
    }

}