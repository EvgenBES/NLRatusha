package com.blackstone.ratusha.ui.adapter.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.ratusha.databinding.ItemDetailedBinding
import com.blackstone.ratusha.ui.base.recycler.BaseViewHolder

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class RecipeViewHolder (binding: ItemDetailedBinding, viewModel: RecipeViewModel):
    BaseViewHolder<ItemRecipeFull, RecipeViewModel, ItemDetailedBinding>(binding, viewModel) {
    companion object {
        fun create(parent: ViewGroup, viewModel: RecipeViewModel): RecipeViewHolder {
            val binding = ItemDetailedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RecipeViewHolder(binding, viewModel)
        }
    }
}
