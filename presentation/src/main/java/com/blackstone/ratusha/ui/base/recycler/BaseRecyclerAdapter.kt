package com.blackstone.ratusha.ui.base.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */

abstract class BaseRecyclerAdapter<Entity, VM : BaseItemViewModel<Entity>>
    (private var itemList: MutableList<Entity> = mutableListOf<Entity>()) : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>() {

    private val clickItemSubject = MutableLiveData<ItemClick<Entity>>()
    fun onClickItemSubject():LiveData<ItemClick<Entity>> = clickItemSubject

    override fun getItemCount(): Int = itemList.size
    fun getLastIndex(): Int = itemList.lastIndex

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {
        holder.bind(itemList[position], holder.adapterPosition)
    }

    fun getClickItemSubject() = clickItemSubject

    fun getItem(position: Int): Entity? {
        return if (itemList.lastIndex  < position) null else itemList[position]
    }

    fun getListItems(): List<Entity> {
        return itemList
    }

    fun setItems(items: List<Entity>) {
        this.itemList = items.toMutableList()
        notifyDataSetChanged()
    }

    fun itemsRangeChanged(listItems: List<Entity>, positionStart: Int, itemCount: Int) {
        this.itemList = listItems.toMutableList()
        notifyItemRangeChanged(positionStart, itemCount)
    }

    fun addItems(items: List<Entity>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun updateItem(position: Int, item: Entity) {
        itemList[position] = item
        notifyItemChanged(position)
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            clickItemSubject.value = ItemClick(itemList[pos], pos)
            holder.viewModel.onItemClick()
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}