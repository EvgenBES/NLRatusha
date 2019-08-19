package com.blackstone.ratusha.ui.screens.settings

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.Config
import com.blackstone.domain.usecases.GetConfigUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class SettingsModel : BaseViewModel<ControllerRouter>() {

    private val configObserver: Observer<Config> = Observer { config -> setConfig(config) }
    val closeDialog = MutableLiveData<Boolean>()

    val checkTpForpost = ObservableBoolean()
    val checkTpOctal = ObservableBoolean()
    val checkStatusForpost = ObservableBoolean()
    val checkStatusOctal = ObservableBoolean()

    @Inject lateinit var configUseCase: GetConfigUseCase

    init {
        App.appComponent.runInject(this)
        configUseCase.getConfig().observeForever(configObserver)
    }

    override fun onCleared() {
        configUseCase.getConfig().removeObserver(configObserver)
        super.onCleared()
    }


    private fun setConfig(config: Config?) {
        config?.let { _config ->
            checkTpForpost.set(_config.tpForpost)
            checkTpOctal.set(_config.tpOctal)
            checkStatusForpost.set(_config.statusForpost)
            checkStatusOctal.set(_config.statusOctal)
        }
    }

    fun onClickSave() {
        configUseCase.execute( Config(
                checkTpForpost.get(),
                checkTpOctal.get(),
                checkStatusForpost.get(),
                checkStatusOctal.get())) {
            onComplete { closeDialog.value = true }
        }
    }

    fun onClickBack() {
        closeDialog.value = true
    }

}
