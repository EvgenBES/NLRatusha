package com.blackstone.ratusha.ui.screens.forpost

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.domain.entity.TownHall
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.usecases.GetInfoTownHallUseCase
import com.blackstone.domain.usecases.GetItemForpostUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.adapter.items.ItemsAdapter
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.ui.screens.detailed.DetailItemFragment
import com.blackstone.ratusha.utils.CalculationsUtils
import com.blackstone.ratusha.utils.CalculationsUtils.countProgress
import com.blackstone.ratusha.utils.Const.FORPOST_ID
import com.blackstone.ratusha.utils.DisplayUtils
import com.blackstone.ratusha.utils.TimerUtils
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class FForpostModel : BaseViewModel<ControllerRouter>() {

    val adapter = ItemsAdapter()

    private val progress = ObservableField<Int>()
    private val closeDoor = ObservableBoolean()
    private val paid = ObservableField<String>("0 / 0")
    private val remainder = ObservableField<String>("Еще: 0")
    private val remainderTimeOrderForpost = ObservableField<String>("9д 23:59:59")
    private var timeOrderForpostNoCast: String = TimerUtils.getDefTimerOrder()

    private val itemsOrder: Observer<List<ItemOrder>> = Observer { list -> setItems(list) }
    private val fpTownHall: Observer<TownHall> = Observer { data -> updateTimeOrder(data) }
    private val onClickAdapter: Observer<ItemClick<ItemOrder>> = Observer { onClickItem(it) }

    @Inject lateinit var getItemForpost: GetItemForpostUseCase
    @Inject lateinit var getInfoTownHall: GetInfoTownHallUseCase

    init {
        App.appComponent.runInject(this)
        getItemForpost.getAllItemOrder().observeForever(itemsOrder)
        getInfoTownHall.getTownHall(FORPOST_ID).observeForever(fpTownHall)
        adapter.onClickItemSubject().observeForever(onClickAdapter)
        startTimer()
    }

    override fun onCleared() {
        getItemForpost.getAllItemOrder().removeObserver(itemsOrder)
        getInfoTownHall.getTownHall(FORPOST_ID).removeObserver(fpTownHall)
        adapter.onClickItemSubject().removeObserver(onClickAdapter)
        super.onCleared()
    }

    fun getTimeOrder(): ObservableField<String> = remainderTimeOrderForpost
    fun getPaid(): ObservableField<String> = paid
    fun getRemainder(): ObservableField<String> = remainder
    fun getProgress(): ObservableField<Int> = progress
    fun getCloseDoor(): ObservableBoolean = closeDoor

    private fun setItems(list: List<ItemOrder>) {
        closeDoor.set(list.isEmpty())
        adapter.setItems(list.convertToLinkedList())
        setTotalSumOrder(list)
    }

    private fun updateTimeOrder(data: TownHall?) {
        data?.let { timeOrderForpostNoCast = data.finish }
    }

    private fun setTotalSumOrder(listItem: List<ItemOrder>) {
        val result = CalculationsUtils.totalSum(listItem)

        paid.set("${result.paid} / ${result.total}")
        remainder.set("Еще: ${result.remainder}")
        progress.set(
            (countProgress(listItem).toInt() * 1.48 * DisplayUtils.getDensityDouble(
                router?.activity ?: return
            )).toInt()
        )
    }

    private fun startTimer() {
        TimerUtils.repeatAfter1Sec {
            remainderTimeOrderForpost.set(
                TimerUtils.timeMap(
                    timeOrderForpostNoCast
                )
            )
        }
    }

    private fun onClickItem(item: ItemClick<ItemOrder>) {
        router?.startReplaceFragment(DetailItemFragment.getInstance(item.item.id))
    }
}