package com.blackstone.ratusha.ui.base.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */

abstract class BaseRecyclerAdapter<Entity, VM : BaseItemViewModel<Entity>>
    (private var itemList: LinkedList<Entity> = LinkedList()) : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>() {

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

    fun getListItems(): LinkedList<Entity> {
        return itemList
    }

    fun setItems(items: LinkedList<Entity>) {
        this.itemList = items
        notifyDataSetChanged()
    }

    fun itemsRangeChanged(listItems: LinkedList<Entity>, positionStart: Int, itemCount: Int) {
        this.itemList = listItems
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

    open fun moveItem(fromPosition: Int, toPosition: Int = 1) {
        val item: Entity = itemList[fromPosition]
        itemList.removeAt(fromPosition)
        itemList.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)

        val positionStart = if (fromPosition < toPosition) fromPosition else toPosition
        val itemCount = Math.abs(fromPosition - toPosition) + 1
        notifyItemRangeChanged(positionStart, itemCount)
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