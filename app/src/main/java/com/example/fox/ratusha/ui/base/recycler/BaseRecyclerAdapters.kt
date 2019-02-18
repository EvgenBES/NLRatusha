package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import io.reactivex.subjects.PublishSubject

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
//abstract class BaseRecyclerAdapters<Entity, VM : BaseItemViewModel<Entity>>
//(val itemList: MutableList<Entity> = mutableListOf()) : RecyclerView.Adapter<BaseViewHolder<Entity, VM>>() {
//
//    val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()
//
//    override fun getItemCount(): Int = itemList.size
//
//    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM>, position: Int) {
//        holder.bind(itemList[position], position)
//    }
//
//    fun addItems(items: List<Entity>) {
//        val startPos = itemList.size
//        itemList.addAll(items)
//        notifyItemRangeChanged(startPos, items.size)
//    }
//
//    fun cleanItems() {
//        itemList.clear()
//        notifyDataSetChanged()
//    }
//
//    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM>) {
//        super.onViewAttachedToWindow(holder)
//        holder.itemView.setOnClickListener {
//            val pos = holder.adapterPosition
//            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
//            holder.viewModel.onItemClick()
//        }
//    }
//
//    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM>) {
//        super.onViewDetachedFromWindow(holder)
//        holder.itemView.setOnClickListener(null)
//    }
//}