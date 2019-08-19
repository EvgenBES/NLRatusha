package com.blackstone.ratusha.ui.screens.octal

import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.usecases.GetInfoTownHall
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.adapter.RecyclerItemRatushaAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils
import com.blackstone.ratusha.utils.DisplayUtils
import com.blackstone.ratusha.utils.TimerUtils
import com.blackstone.domain.entity.TownHall
import com.blackstone.ratusha.utils.Const
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 21.04.2019
 */
class FOctalModel : BaseViewModel<ControllerRouter>() {

    val adapter = RecyclerItemRatushaAdapter(type = 1)

    private val progress = ObservableField<Int>()
    private val paid = ObservableField<String>("0 / 0")
    private val remainder = ObservableField<String>("Еще: 0")

    private val remainderTimeOrderOctal = ObservableField<String>("9д 23:59:59")
    private var timeOrderOctalNoCast: String = TimerUtils.getDefTimerOrder()

    private val itemsOrder: Observer<List<ItemOrder>> = Observer { list -> setItems(list) }
    private val fpTownHall: Observer<TownHall> = Observer { data -> timeOrderOctalNoCast = data.finish }

    @Inject lateinit var getItemOctal: GetItemOctalUseCase
    @Inject lateinit var getInfoTownHall: GetInfoTownHall

    init {
        App.appComponent.runInject(this)
        getItemOctal.getAllItemOrder().observeForever(itemsOrder)
        getInfoTownHall.getTownHall(Const.OCTAL_ID).observeForever(fpTownHall)
        startTimer()
    }

    private fun setItems(list: List<ItemOrder>) {
        adapter.setItems(list)
        setTotalSumOrder(list)
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
        TimerUtils.repeatAfter1Sec { remainderTimeOrderOctal.set(TimerUtils.timeMap(timeOrderOctalNoCast)) }
    }
}