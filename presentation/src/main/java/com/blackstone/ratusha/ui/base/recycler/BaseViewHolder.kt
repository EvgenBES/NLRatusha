package com.blackstone.ratusha.ui.base.recycler

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */

abstract class BaseViewHolder<Entity, VM:BaseItemViewModel<Entity>, Binding: ViewDataBinding>
    (val binding: Binding, val viewModel: VM): RecyclerView.ViewHolder(binding.root)
{
    init {
        binding.setVariable(BR.viewModel, viewModel)
    }
    fun bind(item: Entity, position: Int){
        viewModel.bindItem(item, position)
        binding.executePendingBindings()
    }
}