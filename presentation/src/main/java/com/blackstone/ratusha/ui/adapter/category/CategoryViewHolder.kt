package com.blackstone.ratusha.ui.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.databinding.ItemCategoryBinding
import com.blackstone.ratusha.ui.base.recycler.BaseViewHolder

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class CategoryViewHolder(binding: ItemCategoryBinding, viewModel: CategoryViewModel):
    BaseViewHolder<ItemCategory, CategoryViewModel, ItemCategoryBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: CategoryViewModel): CategoryViewHolder {
            val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryViewHolder(binding, viewModel)
        }
    }
}