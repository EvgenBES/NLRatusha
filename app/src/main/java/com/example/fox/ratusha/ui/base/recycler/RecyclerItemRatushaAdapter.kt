package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.ui.entity.ItemOrder
import kotlinx.android.synthetic.main.item_order_recycler.view.*
import android.text.Html
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.screens.detailed.DetailItemInfo
import com.example.fox.ratusha.ui.widget.ExpandableCardView
import com.example.fox.ratusha.utils.CalculationsUtils.calculatePercent
import com.example.fox.ratusha.utils.CalculationsUtils.totalRemainderCardView
import com.example.fox.ratusha.utils.CalculationsUtils.transformTotalSum


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
            itemView.exp_cardview
        }

        fun onBind(position: Int) {
            val (id, itemName, urlImage, countStart, countFinish, price) = itemList[position]
            inflateData(itemName, urlImage, countStart, countFinish, price)
            setItemClickListener(id)
        }

        private fun setItemClickListener(id: Int) {
            itemView.setOnClickListener {
                itemView.context.startActivity(DetailItemInfo.getInstance(itemView.context, id))
            }

        }

        private fun inflateData(itemName: String, urlImage: String, countStart: Int, countFinish: Int, price: Int) {
            val card = itemView.findViewById<ExpandableCardView>(R.id.exp_cardview)

            val image = urlImage.replace(".gif", "")
            val resourceId = itemView.context?.resources?.getIdentifier("ic_$image", "drawable", itemView.context.packageName)
            val iconEmpty: Int = itemView.context?.resources?.getIdentifier("ic_iw_empty", "drawable", itemView.context.packageName) ?: 0

            card.exp_cardview.cardImage = resourceId ?: iconEmpty
            card.exp_cardview.cardTitle = itemName
            card.exp_cardview.cardPrice = price.toString()
            card.exp_cardview.cardProgress = calculatePercent((price * countFinish), (price * countStart)).toInt()
            card.exp_cardview.cardTotal = "${transformTotalSum((price * countStart))} / ${transformTotalSum((price * countFinish))}"

            if (countStart != countFinish) {
                val tvColor = "<font color='red'>$countStart</font>/$countFinish"
                card.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
                card.exp_cardview.cardRemainder = "(еще: ${countFinish - countStart})"
            } else {
                val tvColor = "<font color='green'>$countStart/$countFinish</font>"
                card.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
                card.exp_cardview.cardRemainder = ""
            }

            if (totalRemainderCardView(price, countStart, countFinish)) {
                card.exp_cardview.cardTotalRemain = "Еще: ${transformTotalSum((price * countFinish) - (price * countStart))}"
            } else {
                card.exp_cardview.cardTotalRemain = ""
            }
        }

    }
}