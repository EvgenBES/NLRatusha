package com.blackstone.ratusha.ui.adapter.category

import androidx.databinding.ObservableField
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.ui.base.recycler.BaseItemViewModel

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class CategoryViewModel: BaseItemViewModel<ItemCategory>() {

    private val image: ObservableField<String> = ObservableField<String>()
    private val title: ObservableField<String> = ObservableField<String>()

    fun getImage(): ObservableField<String> = image
    fun getTitle(): ObservableField<String> = title

    override fun bindItem(item: ItemCategory, position: Int) {
        image.set(item.image)
        title.set(item.name)
    }
}