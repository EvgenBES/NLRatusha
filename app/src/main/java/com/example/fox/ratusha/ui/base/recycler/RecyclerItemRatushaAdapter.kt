package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.ui.entity.ItemOrder
import kotlinx.android.synthetic.main.item_order_recycler.view.*
import android.text.Html


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
//            itemView.bnt_arrow_down.setOnClickListener {
//                val transition = ChangeBounds()
//                transition.duration = 200L
//
//                TransitionManager.beginDelayedTransition(itemView.expanded_layout,transition)
//                TransitionManager.beginDelayedTransition(itemView.wrap_content,transition)
//                if (itemView.expanded_layout.visibility == View.GONE) itemView.expanded_layout.visibility = View.VISIBLE else itemView.expanded_layout.visibility = View.GONE
//            }
//
//            itemView.bottomWrapper.setOnClickListener {
//                if (itemView.bottomWrapper.visibility == View.VISIBLE) itemView.bottomWrapper.visibility = View.INVISIBLE
//            }
        }

        private fun inflateData(id: Int, itemName: String, urlImage: String, countStart: Int, countFinish: Int) {
            val resourceId = itemView.context?.resources?.getIdentifier(urlImage, "drawable", itemView.context.packageName)
            itemView.exp_cardview.cardImage = resourceId ?: 0
            itemView.exp_cardview.cardTitle = itemName

            if (countStart != countFinish) {
                val tvColor = "<font color='red'>$countStart</font>/$countFinish"
                itemView.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
                itemView.exp_cardview.cardRemainder = "(еще: ${countFinish - countStart})"
            } else {
                val tvColor = "<font color='green'>$countStart/$countFinish</font>"
                itemView.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
            }

        }

    }
}