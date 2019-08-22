package com.blackstone.ratusha.ui.adapter.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.ratusha.databinding.ItemOrderBinding
import com.blackstone.ratusha.ui.base.recycler.BaseViewHolder

/**
 * @author Evgeny Butov
 * @created 22.08.2019
 */
class ItemsViewHolder (binding: ItemOrderBinding, viewModel: ItemsViewModel):
BaseViewHolder<ItemOrder, ItemsViewModel, ItemOrderBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: ItemsViewModel): ItemsViewHolder {
            val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemsViewHolder(binding, viewModel)
        }
    }
}