package com.blackstone.ratusha.ui.adapter.category

import android.view.ViewGroup
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.ui.base.recycler.BaseRecyclerAdapter

/**
 * @author Evgeny Butov
 * @created 21.08.2019
 */
class CategoryAdapter: BaseRecyclerAdapter<ItemCategory, CategoryViewModel>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): CategoryViewHolder {
        return CategoryViewHolder.create(viewGroup,  CategoryViewModel())
    }
}