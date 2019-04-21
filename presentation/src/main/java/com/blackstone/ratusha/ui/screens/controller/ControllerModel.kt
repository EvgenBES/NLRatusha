package com.blackstone.ratusha.ui.screens.controller

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

    @Inject
    lateinit var updateDataBase: UpdateDataUseCase

    init {
        App.appComponent.runInject(this)
        getOrderInformation()
        startTimer()
    }

    fun getOrderInformation() {
        updateDataBase.updateDataForpost().andThen { observer -> observer.onComplete() }
            .subscribeBy(onError = { })

        updateDataBase.updateDataOctal().andThen { observer -> observer.onComplete() }
            .subscribeBy(
                onError = { error ->
                    router?.showError(error.message.let { "Error" })
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