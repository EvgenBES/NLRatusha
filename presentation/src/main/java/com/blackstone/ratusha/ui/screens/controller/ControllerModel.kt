package com.blackstone.ratusha.ui.screens.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blackstone.domain.usecases.UpdateForpostDataUseCase
import com.blackstone.domain.usecases.UpdateOctalDataUseCase
import com.blackstone.notif.NotificationR
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 13.04.2019
 */
class ControllerModel : BaseViewModel<ControllerRouter>() {

    companion object {
        const val UPDATE_TIME = "updateTime"
    }

    var stateRecyclerFragment: Boolean = false
    private val buttonRefresh = MutableLiveData<Boolean>()
    private val progressBar = MutableLiveData<Boolean>()

    @Inject lateinit var updateOctalData: UpdateOctalDataUseCase
    @Inject lateinit var updateForpostData: UpdateForpostDataUseCase
    @Inject lateinit var notificationR: NotificationR

    init {
        App.appComponent.runInject(this)
        getOrderInformation()
    }

    fun getButtonRefresh(): LiveData<Boolean> = buttonRefresh
    fun getProgressBar(): LiveData<Boolean> = progressBar

    private fun getOrderInformation() {
        viewModelScope.launch {
            updateOctalData.execute { }

            updateForpostData.execute {
                onComplete {
                    viewModelScope.launch {
                        delay(1250)
                        progressBar.value = false
                    }
                }
                onError {
                    buttonRefresh.value = false
                    progressBar.value = false
                }
            }
        }
    }

    fun refreshData() {
        buttonRefresh.value = true
        progressBar.value = true
        getOrderInformation()
    }

    fun openSettings() {
        router?.openSettings()
    }

}