package com.blackstone.ratusha.ui.screens.settings

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.util.Log
import com.blackstone.domain.entity.Config
import com.blackstone.domain.usecases.GetConfigUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class SettingsModel : BaseViewModel<ControllerRouter>() {

    val closeDialog = MutableLiveData<Boolean>()

    val checkTpForpost = ObservableBoolean()
    val checkTpOctal = ObservableBoolean()
    val checkStatusForpost = ObservableBoolean()
    val checkStatusOctal = ObservableBoolean()

    companion object {
        const val TAG = "SettingsModel"
    }

    @Inject
    lateinit var configUseCase: GetConfigUseCase

    init {
        App.appComponent.runInject(this)
        getConfigDao()
    }

    private fun getConfigDao() {
        addToDisposable(configUseCase.getConfig()
            .subscribeBy(
                onNext = { setConfig(it) },
                onError = { Log.d(TAG, "get config error") }
            )
        )
    }

    private fun setConfig(config: Config) {
        checkTpForpost.set(config.tpForpost)
        checkTpOctal.set(config.tpOctal)
        checkStatusForpost.set(config.statusForpost)
        checkStatusOctal.set(config.statusOctal)
    }

    fun onClickSave() {
        addToDisposable(configUseCase.update(
            Config(
                checkTpForpost.get(),
                checkTpOctal.get(),
                checkStatusForpost.get(),
                checkStatusOctal.get())
        ).subscribeBy(
            onComplete = { closeDialog.value = true  },
            onError = { Log.d(TAG, "update config error") }
        ))
    }

    fun onClickBack() {
        closeDialog.value = true
    }

}