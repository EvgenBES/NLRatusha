package com.blackstone.ratusha.ui.adapter.recipe

import androidx.databinding.ObservableField
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.entity.TypeRecipe
import com.blackstone.domain.extension.twoCharAfterDot
import com.blackstone.domain.extension.zeroCharAfterDot
import com.blackstone.ratusha.ui.base.recycler.BaseItemViewModel

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class RecipeViewModel: BaseItemViewModel<ItemRecipeFull>() {

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

        if (item.typeRecipe == TypeRecipe.DEFAULT) {
            quantity.set(item.number.twoCharAfterDot())
            price.set(item.price.toString())
            cost.set((item.number * item.price).zeroCharAfterDot())
        } else  {
            quantity.set(item.type)
            price.set(if (item.number.toInt() == 0 || item.number.toInt() == 14) "${item.number.toInt()}м" else "${item.number.toInt()}д")
            cost.set(item.price.toString())
        }

    }
}