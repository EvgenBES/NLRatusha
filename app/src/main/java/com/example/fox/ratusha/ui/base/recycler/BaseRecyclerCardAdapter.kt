package com.example.fox.ratusha.ui.base.recycler

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.entity.ItemOrder
import com.example.fox.ratusha.ui.screens.detailed.DetailItemInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order_recycler.view.*

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class BaseRecyclerCardAdapter(var itemList: MutableList<ItemOrder> = mutableListOf()) : RecyclerView.Adapter<BaseRecyclerCardAdapter.BaseViewHolder>() {

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_recycler, parent, false))


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
            itemView.topImage.setOnClickListener {
                itemView.context.startActivity(DetailItemInfo.getInstance(itemView.context, id))
            }

            itemView.topWrapper.setOnClickListener {
                if (itemView.bottomWrapper.visibility != View.VISIBLE) itemView.bottomWrapper.visibility = View.VISIBLE
            }

            itemView.bottomWrapper.setOnClickListener {
                if (itemView.bottomWrapper.visibility == View.VISIBLE) itemView.bottomWrapper.visibility = View.INVISIBLE
            }
        }

        @SuppressLint("SetTextI18n")
        private fun inflateData(id: Int, itemName: String, urlImage: String, countStart: Int, countFinish: Int) {
            Picasso.get()
                    .load("http://image.neverlands.ru/weapon/$urlImage")
                    .placeholder(R.drawable.ic_hourglass)
                    .error(R.drawable.ic_cancel)
                    .into(itemView.image_item)

            itemName.let { itemView.nameItem.text = it }

            val color = if (countStart != countFinish) "red" else "green"
            val textColor = "<font color='$color'>($countStart/$countFinish)</font>"

            itemView.counterItem.text = Html.fromHtml(textColor)

        }

    }
}