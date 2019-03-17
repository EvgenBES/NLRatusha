package com.example.fox.ratusha.ui.base.recycler

import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.ui.entity.ItemOrder
import kotlinx.android.synthetic.main.item_order_recycler.view.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.Log
import java.lang.Thread.sleep


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerItemRatushaAdapter(var itemList: MutableList<ItemOrder> = mutableListOf()) : RecyclerView.Adapter<RecyclerItemRatushaAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(com.example.fox.ratusha.R.layout.item_order_recycler, parent, false))


    fun setItems(items: List<ItemOrder>) {
        this.itemList = items as MutableList<ItemOrder>
        notifyDataSetChanged()
    }


    internal fun addItemssToList(items: List<ItemOrder>) {
        val startPos = itemList.size
        itemList.addAll(items)
        notifyItemRangeChanged(startPos, items.size)
    }

    fun cleanItems() {
        itemList.clear()
        notifyDataSetChanged()
    }

    inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun clear() {
//            itemView.coverImageView.setImageDrawable(null)
//            itemView.titleTextView.text = ""
//            itemView.contentTextView.text = ""
        }

        fun onBind(position: Int) {
            val (id, itemName, urlImage, countStart, countFinish) = itemList[position]
            inflateData(id, itemName, urlImage, countStart, countFinish)
            setItemClickListener(id)
        }

        private fun setItemClickListener(id: Int) {
//            itemView.topImage.setOnClickListener {
//                itemView.context.startActivity(DetailItemInfo.getInstance(itemView.context, id))
//            }
//
            itemView.bnt_arrow_down.setOnClickListener {
                val transition = ChangeBounds()
                transition.duration = 200L

                TransitionManager.beginDelayedTransition(itemView.expanded_layout,transition)
                TransitionManager.beginDelayedTransition(itemView.wrap_content,transition)
                if (itemView.expanded_layout.visibility == View.GONE) itemView.expanded_layout.visibility = View.VISIBLE else itemView.expanded_layout.visibility = View.GONE
            }
//
//            itemView.bottomWrapper.setOnClickListener {
//                if (itemView.bottomWrapper.visibility == View.VISIBLE) itemView.bottomWrapper.visibility = View.INVISIBLE
//            }
        }

        private fun inflateData(id: Int, itemName: String, urlImage: String, countStart: Int, countFinish: Int) {
//            Picasso.get()
//                    .load("http://image.neverlands.ru/weapon/$urlImage")
//                    .placeholder(R.drawable.ic_hourglass)
//                    .error(R.drawable.ic_cancel)
//                    .into(itemView.image_item)
//
//            itemName.let { itemView.nameItem.text = it }
//
//            val color = if (countStart != countFinish) "red" else "green"
//            val textColor = "<font color='$color'>($countStart/$countFinish)</font>"
//
//            itemView.counterItem.text = Html.fromHtml(textColor)

        }

    }
}