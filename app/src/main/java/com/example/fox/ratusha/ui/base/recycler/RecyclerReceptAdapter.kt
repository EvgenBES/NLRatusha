package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.entity.ItemRecept
import kotlinx.android.synthetic.main.item_detailed_item.view.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerReceptAdapter(var itemList: MutableList<ItemRecept> = mutableListOf()) : RecyclerView.Adapter<RecyclerReceptAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detailed_item, parent, false))

    fun setItems(items: List<ItemRecept>) {
        this.itemList = items as MutableList<ItemRecept>
        notifyDataSetChanged()
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (urlImage, itemName, quentity, price) = itemList[position]
            inflateData(urlImage, itemName, quentity, price)
        }


        private fun inflateData(image: String, itemName: String, quentity: Double, price: Int) {
            val resourceId = itemView.context?.resources?.getIdentifier(image, "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.image_item.setImageResource(resourceId)
            itemView.item_name.text = itemName
            itemView.item_quantity.text = quentity.toString()
            itemView.item_price.text = price.toString()
            itemView.item_cost.text = (quentity * price).toString()
        }
    }

}