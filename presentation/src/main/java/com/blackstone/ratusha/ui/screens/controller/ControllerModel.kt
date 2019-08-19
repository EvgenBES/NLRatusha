package com.blackstone.ratusha.ui.screens.controller

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.blackstone.data.extension.defaultSharedPreferences
import com.blackstone.domain.usecases.UpdateForpostDataUseCase
import com.blackstone.domain.usecases.UpdateOctalDataUseCase
import com.blackstone.notif.NotificationR
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import java.util.*
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
    val stateData = MutableLiveData<Boolean>()

    @Inject
    lateinit var updateOctalData: UpdateOctalDataUseCase

    @Inject
    lateinit var updateForpostData: UpdateForpostDataUseCase

    @Inject
    lateinit var notificationR: NotificationR

    init {
        App.appComponent.runInject(this)
        getOrderInformation()
    }

    private fun getOrderInformation() {
        updateOctalData.execute {
            onComplete { stateData.value = it }
            onError { stateData.value = false }
        }

        updateForpostData.execute {
            onComplete {
                stateData.value = it
                setTimeUpdateSharedPref(router?.activity, Date().time)
            }
            onError { stateData.value = false }
        }
    }

    fun refreshData() {
        getOrderInformation()
    }

    fun openSettings() {
        router?.openSettings()
    }

    private fun setTimeUpdateSharedPref(context: Context?, time: Long) {
        context?.let {
            val sharedPref = context.defaultSharedPreferences
            sharedPref.edit().putLong(UPDATE_TIME, time).apply()
        }
    }
}