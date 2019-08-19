package com.blackstone.ratusha.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.blackstone.ratusha.R
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import kotlinx.android.synthetic.main.item_category_recycler.view.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerCategoryAdapter(var itemList: MutableList<ItemCategory> = mutableListOf()) : RecyclerView.Adapter<RecyclerCategoryAdapter.BaseViewHolder>() {

    val clickItemSubject = MutableLiveData<ItemClick<ItemCategory>>()
    val clickDetailItemInfo = MutableLiveData<ItemClick<ItemCategory>>()

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
                if (itemList[position].image.contains("prof_")) clickItemSubject.postValue(
                    ItemClick(
                        itemList[position],
                        position
                    )
                )
                else clickDetailItemInfo.postValue(
                    ItemClick(
                        itemList[position],
                        position
                    )
                )
            }
        }

        private fun inflateData(id: Int, itemName: String, image: String) {
            val resourceId = itemView.context?.resources?.getIdentifier("ic_$image", "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.imageItemCategory.setImageResource(resourceId)
            itemView.nameItemCategory.text = itemName
        }
    }

}