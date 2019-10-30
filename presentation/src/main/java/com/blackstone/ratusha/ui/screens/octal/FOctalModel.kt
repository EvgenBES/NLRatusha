package com.blackstone.ratusha.ui.screens.octal

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.usecases.GetInfoTownHallUseCase
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.adapter.items.ItemsAdapter
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.ui.screens.detailed.DetailItemFragment
import com.blackstone.ratusha.utils.CalculationsUtils
import com.blackstone.ratusha.utils.Const
import com.blackstone.ratusha.utils.Const.OCTAL_ADAPTER
import com.blackstone.ratusha.utils.DisplayUtils
import com.blackstone.ratusha.utils.TimerUtils
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 21.04.2019
 */
class FOctalModel : BaseViewModel<ControllerRouter>() {

    val adapter = ItemsAdapter(type = OCTAL_ADAPTER)

    private val progress = ObservableField<Int>()
    private val closeDoor = ObservableBoolean()
    private val paid = ObservableField<String>()
    private val remainder = ObservableField<String>()
    private val remainderTimeOrderOctal = ObservableField<String>()
    private var timeOrderOctalNoCast: String = TimerUtils.getDefTimerOrder()

    private val itemsOrder: Observer<List<ItemOrder>> = Observer { list -> setItems(list) }
    private val fpTownHall: Observer<TownHall> = Observer { data ->  updateTimeOrder(data) }
    private val onClickAdapter: Observer<ItemClick<ItemOrder>> = Observer { onClickItem(it) }

    @Inject lateinit var getItemOctal: GetItemOctalUseCase
    @Inject lateinit var getInfoTownHall: GetInfoTownHallUseCase

    init {
        App.appComponent.runInject(this)
        getItemOctal.getAllItemOrder().observeForever(itemsOrder)
        getInfoTownHall.getTownHall(Const.OCTAL_ID).observeForever(fpTownHall)
        adapter.onClickItemSubject().observeForever(onClickAdapter)
        startTimer()
    }

    override fun onCleared() {
        getItemOctal.getAllItemOrder().observeForever(itemsOrder)
        getInfoTownHall.getTownHall(Const.OCTAL_ID).observeForever(fpTownHall)
        adapter.onClickItemSubject().observeForever(onClickAdapter)
        super.onCleared()
    }

    fun getTimeOrder(): ObservableField<String> = remainderTimeOrderOctal
    fun getPaid(): ObservableField<String> = paid
    fun getRemainder(): ObservableField<String>  = remainder
    fun getProgress(): ObservableField<Int> = progress
    fun getCloseDoor(): ObservableBoolean = closeDoor

    private fun setItems(list: List<ItemOrder>) {
        closeDoor.set(list.isEmpty())
        adapter.setItems(list)
        setTotalSumOrder(list)
    }

    private fun updateTimeOrder(data: TownHall?) {
        data?.let { timeOrderOctalNoCast = data.finish }
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

    private fun onClickItem(item: ItemClick<ItemOrder>) {
        router?.startReplaceFragment(DetailItemFragment.getInstance(item.item.id), DetailItemFragment.TAG)
    }
}