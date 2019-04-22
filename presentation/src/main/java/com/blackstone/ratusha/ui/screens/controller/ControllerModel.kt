package com.blackstone.ratusha.ui.screens.controller

import android.arch.lifecycle.MutableLiveData
import com.blackstone.domain.usecases.UpdateDataUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.utils.TimerUtils
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
class ControllerModel : BaseViewModel<ControllerRouter>() {

    companion object {
        const val TAG = "Ratusha ControllerModel"
    }

    var stateRecyclerFragment: Boolean = false
    val stateData = MutableLiveData<Boolean>()

    @Inject
    lateinit var updateDataBase: UpdateDataUseCase

    init {
        App.appComponent.runInject(this)
        getOrderInformation()
        startTimer()
    }

    fun getOrderInformation() {
        updateDataBase.updateDataForpost().subscribeBy(onError = { })

        updateDataBase.updateDataOctal()
            .subscribeBy(
                onNext = { stateData.value = true },
                onError = { error -> router?.showError(error.message.let { "Error connect..." })
                    stateData.value = false
                }
            )
    }

    /**
     * This getting new information from internet
     * once in 5 minutes
     */
    private fun startTimer() {
        val disposable = TimerUtils.observable5m.subscribe(
            { getOrderInformation() },
            { e -> println("$TAG startTimer: $e") },
            { }
        )
        addToDisposable(disposable)
    }

}