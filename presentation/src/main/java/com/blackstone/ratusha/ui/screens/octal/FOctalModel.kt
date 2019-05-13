package com.blackstone.ratusha.ui.screens.octal

import android.databinding.ObservableField
import android.util.Log
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.usecases.GetInfoTownHall
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.RecyclerItemRatushaAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils
import com.blackstone.ratusha.utils.DisplayUtils
import com.blackstone.ratusha.utils.TimerUtils
import com.blackstone.domain.entity.Town.OCTAL
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 21.04.2019
 */
class FOctalModel : BaseViewModel<ControllerRouter>() {

    companion object {
        const val TAG = "Ratusha FOctalModel"
    }

    val adapter = RecyclerItemRatushaAdapter(type = 1)

    private val progress = ObservableField<Int>()
    private val paid = ObservableField<String>("0 / 0")
    private val remainder = ObservableField<String>("Еще: 0")

    private val remainderTimeOrderOctal = ObservableField<String>("9д 23:59:59")
    private var timeOrderOctalNoCast: String = TimerUtils.getDefTimerOrder()

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    @Inject
    lateinit var getInfoTownHall: GetInfoTownHall

    init {
        App.appComponent.runInject(this)
        getItems()
        getTownHall()
        startTimer()
    }

    private fun getItems() {
        val disposable = getItemOctal.getAllItemOrder()
            .subscribeBy(
                onNext = {
                    adapter.setItems(it)
                    setTotalSumOrder(it)
                },
                onError = { Log.d(TAG, "Error message: ${it.message}") }
            )
        addToDisposable(disposable)
    }

    /**
     * Get remainder time order from database
     */
    private fun getTownHall() {
        val disposable = getInfoTownHall.getTownHall(OCTAL.getId()).subscribeBy(
            onNext = { timeOrderOctalNoCast = it.finish },
            onError = { Log.d(TAG, "getTownHall message: ${it.message}") }
        )
        addToDisposable(disposable)
    }

    fun getTimeOrder(): ObservableField<String> {
        return remainderTimeOrderOctal
    }

    fun getPaid(): ObservableField<String> {
        return paid
    }

    fun getRemainder(): ObservableField<String> {
        return remainder
    }

    fun getProgress(): ObservableField<Int> {
        return progress
    }

    private fun setTotalSumOrder(listItem: List<ItemOrder>) {
        val result = CalculationsUtils.totalSum(listItem)

        paid.set("${result.paid} / ${result.total}")
        remainder.set("Еще: ${result.remainder}")
        progress.set((CalculationsUtils.countProgress(listItem).toInt() * 1.48 * DisplayUtils.getDensityDouble(router?.activity ?: return)).toInt())
    }

    private fun startTimer() {
        val disposable = TimerUtils.observable1s.subscribe(
            { remainderTimeOrderOctal.set(TimerUtils.timeMap(timeOrderOctalNoCast)) },
            { e -> println("$TAG startTimer: $e") }
        )
        addToDisposable(disposable)
    }
}