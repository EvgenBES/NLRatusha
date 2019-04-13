package com.blackstone.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackstone.ratusha.R
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.ui.screens.detailed.DetailItemInfo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_category_recycler.view.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerCategoryAdapter(var itemList: MutableList<ItemCategory> = mutableListOf()) : RecyclerView.Adapter<RecyclerCategoryAdapter.BaseViewHolder>() {

    val clickItemSubject = PublishSubject.create<ItemClick<ItemCategory>>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_recycler, parent, false))

    fun setItems(items: List<ItemCategory>) {
        this.itemList = items as MutableList<ItemCategory>
        notifyDataSetChanged()
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (id, itemName, urlImage) = itemList[position]
            inflateData(id, itemName, urlImage)
            setItemClickListener(position)
        }

        private fun setItemClickListener(position: Int) {
            itemView.setOnClickListener {
                if (itemList[position].image.contains("prof_")) clickItemSubject.onNext(ItemClick(itemList[position], position))
                else itemView.context.startActivity(DetailItemInfo.getInstance(itemView.context, itemList[position].id))
            }
        }

        private fun inflateData(id: Int, itemName: String, image: String) {
            val resourceId = itemView.context?.resources?.getIdentifier("ic_$image", "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.imageItemCategory.setImageResource(resourceId)
            itemView.nameItemCategory.text = itemName
        }
    }

}