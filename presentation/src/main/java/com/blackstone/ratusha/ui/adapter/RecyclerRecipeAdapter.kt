package com.blackstone.ratusha.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackstone.ratusha.R
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.ratusha.utils.twoCharAfterDot
import kotlinx.android.synthetic.main.item_detailed_item.view.*
import java.util.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerRecipeAdapter(var itemList: LinkedList<ItemRecipeFull> = LinkedList()) : RecyclerView.Adapter<RecyclerRecipeAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detailed_item, parent, false))

    fun setItems(items: LinkedList<ItemRecipeFull>) {
        this.itemList = items
        notifyDataSetChanged()
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val (id, image, name, price, quantity, type: String) = itemList[position]
            inflateData(image, name, price, quantity, type)
        }


        private fun inflateData(image: String, itemName: String,  price: Int, quantity: Double, type: String) {
            val resourceId = itemView.context?.resources?.getIdentifier("ic_$image", "drawable", itemView.context.packageName)
            val emptyImage = itemView.context?.resources?.getIdentifier("ic_res_empty", "drawable", itemView.context.packageName)

            if (resourceId != null && resourceId != 0) {
                itemView.image_item.setImageResource(resourceId)
            } else {
                if (emptyImage != null && emptyImage != 0) {
                    itemView.image_item.setImageResource(emptyImage)
                }
            }
            itemView.item_name.text = itemName

            if (type.contains("empty")) {
                itemView.item_quantity.text = quantity.toString()
                itemView.item_price.text = price.toString()
                itemView.item_cost.text = (quantity * price).twoCharAfterDot()
            } else {
                /**
                 * Alchemy Item where: quantity it type
                 * price it time and cost it price
                 */
                itemView.item_quantity.text = type
                itemView.item_price.text = if (quantity.toInt() == 0 || quantity.toInt() == 14) "${quantity.toInt()}м" else "${quantity.toInt()}д"
                itemView.item_cost.text = price.toString()
            }
        }
    }

}