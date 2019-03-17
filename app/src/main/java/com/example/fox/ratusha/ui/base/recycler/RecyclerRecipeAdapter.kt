package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.entity.ItemRecipe
import kotlinx.android.synthetic.main.item_detailed_item.view.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerRecipeAdapter(var itemList: MutableList<ItemRecipe> = mutableListOf()) : RecyclerView.Adapter<RecyclerRecipeAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detailed_item, parent, false))

    fun setItems(items: List<ItemRecipe>) {
        this.itemList = items as MutableList<ItemRecipe>
        notifyDataSetChanged()
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (id, image, name, price, quantity) = itemList[position]
            inflateData(id, image, name, price, quantity)
        }


        private fun inflateData(id: Int, image: String, itemName: String,  price: Int, quantity: Double) {
            val resourceId = itemView.context?.resources?.getIdentifier(image, "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.image_item.setImageResource(resourceId)
            itemView.item_name.text = itemName
            itemView.item_quantity.text = quantity.toString()
            itemView.item_price.text = price.toString()
            itemView.item_cost.text = (quantity * price).toString()
        }
    }

}