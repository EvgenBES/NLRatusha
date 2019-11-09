package com.blackstone.ratusha.ui.screens.settings

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.blackstone.domain.entity.Config
import com.blackstone.domain.usecases.config.GetConfigUseCase
import com.blackstone.domain.usecases.config.UpdateConfigUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class SettingsModel : BaseViewModel<ControllerRouter>() {

    private val closeDialog = MutableLiveData<Boolean>()
    private val checkTpForpost = ObservableBoolean()
    private val checkTpOctal = ObservableBoolean()
    private val checkStatusForpost = ObservableBoolean()
    private val checkStatusOctal = ObservableBoolean()

    @Inject lateinit var getConfigUseCase: GetConfigUseCase
    @Inject lateinit var updateConfigUseCase: UpdateConfigUseCase

    init {
        App.appComponent.runInject(this)
        setConfig()
    }

    fun getCheckTpForpost(): ObservableBoolean = checkTpForpost
    fun getCheckTpOctal(): ObservableBoolean = checkTpOctal
    fun getCheckStatusForpost(): ObservableBoolean = checkStatusForpost
    fun getCheckStatusOctal(): ObservableBoolean = checkStatusOctal
    fun getCloseDialog(): MutableLiveData<Boolean> = closeDialog

    private fun setConfig() {
        val config = getConfigUseCase.getConfigSharedProvider()

        checkTpForpost.set(config.tpForpost)
        checkTpOctal.set(config.tpOctal)
        checkStatusForpost.set(config.closeForpost)
        checkStatusOctal.set(config.closeOctal)
    }

    fun onClickSave() {
        updateConfigUseCase.updateConfigSharedProvider(
            Config(
                tpForpost = checkTpForpost.get(),
                tpOctal = checkTpOctal.get(),
                closeForpost = checkStatusForpost.get(),
                closeOctal = checkStatusOctal.get()
            )
        )

        closeDialog.value = true
    }

    fun onClickBack() {
        closeDialog.value = true
    }

}
