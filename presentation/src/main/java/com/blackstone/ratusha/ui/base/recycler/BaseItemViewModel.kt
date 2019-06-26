package com.blackstone.ratusha.ui.base.recycler

import androidx.recyclerview.widget.RecyclerView

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
abstract class BaseItemViewModel<Entity> : RecyclerView.Adapter<RecyclerCategoryAdapter.BaseViewHolder>() {
    open fun onItemClick() {}
}