package com.blackstone.ratusha.ui.adapter.recipe

import androidx.databinding.ObservableField
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.ratusha.ui.base.recycler.BaseItemViewModel
import com.blackstone.ratusha.utils.twoCharAfterDot

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class RecipeViewModel : BaseItemViewModel<ItemRecipeFull>() {

    private val image: ObservableField<String> = ObservableField<String>()
    private val title: ObservableField<String> = ObservableField<String>()
    private val quantity: ObservableField<String> = ObservableField<String>()
    private val price: ObservableField<String> = ObservableField<String>()
    private val cost: ObservableField<String> = ObservableField<String>()

    fun getImage(): ObservableField<String> = image
    fun getTitle(): ObservableField<String> = title
    fun getQuantity(): ObservableField<String> = quantity
    fun getPrice(): ObservableField<String> = price
    fun getCost(): ObservableField<String> = cost

    override fun bindItem(item: ItemRecipeFull, position: Int) {
        image.set(item.image)
        title.set(item.name)

        if (item.type.contains("empty")) {
            quantity.set(item.number.toString())
            price.set(item.price.toString())
            cost.set((item.number * item.price).twoCharAfterDot())
        } else  {
            quantity.set(item.type)
            price.set(if (item.number.toInt() == 0 || item.number.toInt() == 14) "${item.number.toInt()}м" else "${item.number.toInt()}д")
            cost.set(item.price.toString())
        }

    }
}