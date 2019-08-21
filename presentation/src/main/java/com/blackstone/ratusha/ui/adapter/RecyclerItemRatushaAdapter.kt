package com.blackstone.ratusha.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blackstone.domain.entity.ItemOrder
import kotlinx.android.synthetic.main.item_order_recycler.view.*
import android.text.Html
import androidx.lifecycle.MutableLiveData
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.widget.ExpandableCardView
import com.blackstone.ratusha.utils.CalculationsUtils.calculatePercent
import com.blackstone.ratusha.utils.CalculationsUtils.totalRemainderCardView
import com.blackstone.ratusha.utils.CalculationsUtils.transformTotalSum
import java.util.*


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class RecyclerItemRatushaAdapter(val type: Int = 0, var itemList: LinkedList<ItemOrder> = LinkedList()) :
    RecyclerView.Adapter<RecyclerItemRatushaAdapter.BaseViewHolder>() {

    val clickItemSubject = MutableLiveData<ItemClick<ItemOrder>>()

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.let {
        it.clear()
        it.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_recycler, parent, false))


    fun setItems(items: LinkedList<ItemOrder>) {
        this.itemList = items
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
            setItemClickListener(itemList[position], position)
        }

        private fun setItemClickListener(item: ItemOrder, position: Int) {
            itemView.setOnClickListener {
                clickItemSubject.postValue(ItemClick(item, position))
            }
        }

        private fun inflateData(itemName: String, urlImage: String, countStart: Int, countFinish: Int, price: Int) {
            val card = itemView.findViewById<ExpandableCardView>(R.id.exp_cardview)

            val image = urlImage.replace(".gif", "")
            val resourceId =
                itemView.context?.resources?.getIdentifier("ic_$image", "drawable", itemView.context.packageName)
            val iconEmpty: Int =
                itemView.context?.resources?.getIdentifier("ic_iw_empty", "drawable", itemView.context.packageName) ?: 0

            card.exp_cardview.type = type
            card.exp_cardview.cardImage = resourceId ?: iconEmpty
            card.exp_cardview.cardTitle = itemName
            card.exp_cardview.cardPrice = price.toString()
            card.exp_cardview.cardProgress = calculatePercent((price * countFinish), (price * countStart))

            card.exp_cardview.cardTotal =
                "${transformTotalSum((price * countStart))} / ${transformTotalSum((price * countFinish))}"

            if (countStart != countFinish) {
                val tvColor = if (type == 0) {
                    "<font color='red'>$countStart</font>/$countFinish"
                } else {
                    "<font color='#ff1414'>$countStart</font>/$countFinish" //RED
                }
                card.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
                card.exp_cardview.cardRemainder = "(еще: ${countFinish - countStart})"
            } else {
                val tvColor = if (type == 0) {
                    "<font color='green'>$countStart/$countFinish</font>"
                } else {
                    "<font color='#17bd1c'>$countStart/$countFinish</font>" //GREEN
                }
                card.exp_cardview.cardQuantity = Html.fromHtml(tvColor)
                card.exp_cardview.cardRemainder = ""
            }

            if (totalRemainderCardView(price, countStart, countFinish)) {
                card.exp_cardview.cardTotalRemain =
                    "Еще: ${transformTotalSum((price * countFinish) - (price * countStart))}"
            } else {
                card.exp_cardview.cardTotalRemain = ""
            }
        }

    }
}