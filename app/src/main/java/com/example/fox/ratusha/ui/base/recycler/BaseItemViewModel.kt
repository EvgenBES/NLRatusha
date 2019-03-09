package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
abstract class BaseItemViewModel<Entity> : RecyclerView.Adapter<BaseRecyclerListAdapter.BaseViewHolder>() {
    open fun onItemClick() {}
}