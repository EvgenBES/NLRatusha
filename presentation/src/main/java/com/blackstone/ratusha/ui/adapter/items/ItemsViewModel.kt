package com.blackstone.ratusha.ui.adapter.items

import android.text.Html
import android.text.Spanned
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.ratusha.ui.base.recycler.BaseItemViewModel
import com.blackstone.ratusha.utils.CalculationsUtils

/**
 * @author Evgeny Butov
 * @created 22.08.2019
 */
class ItemsViewModel(private val typeAdapter: Int): BaseItemViewModel<ItemOrder>() {

    private val type: ObservableInt = ObservableInt(typeAdapter)
    private val image: ObservableField<String> = ObservableField<String>()
    private val title: ObservableField<String> = ObservableField<String>()
    private val price: ObservableField<String> = ObservableField<String>()
    private val total: ObservableField<String> = ObservableField<String>()
    private val quantity: ObservableField<Spanned> = ObservableField<Spanned>()
    private val remainder: ObservableField<String> = ObservableField<String>()
    private val totalRemain: ObservableField<String> = ObservableField<String>()
    private val progress: ObservableInt = ObservableInt()
    private val isOpenCardView: ObservableBoolean = ObservableBoolean()

    fun getType(): ObservableInt = type
    fun getImage(): ObservableField<String> = image
    fun getTitle(): ObservableField<String> = title
    fun getPrice(): ObservableField<String> = price
    fun getTotal(): ObservableField<String> = total
    fun getQuantity(): ObservableField<Spanned> = quantity
    fun getRemainder(): ObservableField<String> = remainder
    fun getTotalRemain(): ObservableField<String> = totalRemain
    fun getProgress(): ObservableInt = progress

    override fun bindItem(item: ItemOrder, position: Int) {
        image.set("ic_${item.image.replace(".gif", "")}")
        title.set(item.name)
        price.set("${item.price}")
        total.set("${CalculationsUtils.transformTotalSum((item.price * item.countStart))} / " +
                CalculationsUtils.transformTotalSum((item.price * item.countFinish)))
        progress.set(CalculationsUtils.calculatePercent((item.price * item.countFinish), (item.price * item.countStart)))
        getCustomQuantity(item.countStart == item.countFinish, item)
        getCustomTotalRemainder(item)
    }

    private fun getCustomQuantity(isFull: Boolean, item: ItemOrder) {
        if (isFull) {
            val tvColor = if (typeAdapter == 0) {
                "<font color='green'>${item.countStart}/${item.countFinish}</font>"
            } else {
                "<font color='#17bd1c'>${item.countStart}/${item.countFinish}</font>" //GREEN
            }
            quantity.set(Html.fromHtml(tvColor))
            remainder.set("")
        } else {
            val tvColor = if (typeAdapter == 0) {
                "<font color='red'>${item.countStart}</font>/${item.countFinish}"
            } else {
                "<font color='#ff1414'>${item.countStart}</font>/${item.countFinish}" //RED
            }
            quantity.set(Html.fromHtml(tvColor))
            remainder.set("(еще: ${item.countFinish - item.countStart})")
        }
    }

    private fun getCustomTotalRemainder(item: ItemOrder) {
        if (CalculationsUtils.totalRemainderCardView(item.price, item.countStart, item.countFinish)) {
            totalRemain.set("Еще: ${CalculationsUtils.transformTotalSum(
                (item.price * item.countFinish) - (item.price * item.countStart))}")
        } else {
            totalRemain.set("")
        }
    }
}