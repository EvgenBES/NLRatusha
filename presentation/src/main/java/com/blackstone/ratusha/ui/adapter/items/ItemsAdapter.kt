package com.blackstone.ratusha.ui.adapter.items

import android.view.ViewGroup
import com.blackstone.domain.entity.ItemOrder
import com.blackstone.ratusha.ui.base.recycler.BaseRecyclerAdapter

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class ItemsAdapter(val type: Int = 0): BaseRecyclerAdapter<ItemOrder, ItemsViewModel>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ItemsViewHolder {
        return ItemsViewHolder.create(viewGroup,  ItemsViewModel(type))
    }
}