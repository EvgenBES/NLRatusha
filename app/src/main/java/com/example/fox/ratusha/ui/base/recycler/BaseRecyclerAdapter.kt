package com.example.fox.ratusha.ui.base.recycler

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.entity.ItemOrder

/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class BaseRecyclerAdapter(var itemList: MutableList<ItemOrder> = mutableListOf()) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

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
            itemView.setOnClickListener {
                id.let {
                    try {
                        val intent = Intent()
                        // using "with" as an example
                        with(intent) {
                            action = Intent.ACTION_VIEW
                            data = Uri.parse("id")
                            addCategory(Intent.CATEGORY_BROWSABLE)
                        }
                        itemView.context.startActivity(intent)
                    } catch (e: Exception) {
                    }
                }

            }
        }

        private fun inflateData(id: Int, itemName: String, urlImage: String, countStart: Int, countFinish: Int) {
//            title?.let { itemView.titleTextView.text = it }
//            author?.let { itemView.authorTextView.text = it }
//            date?.let { itemView.dateTextView.text = it }
//            description?.let { itemView.contentTextView.text = it }
//            coverPageUrl?.let {
//                itemView.coverImageView.loadImage(it)
//            }
        }

    }
}