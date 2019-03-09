package com.example.fox.ratusha.ui.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.entity.ItemCategory
import com.example.fox.ratusha.ui.screens.detailed.DetailItemInfo
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_category_recycler.view.*
import com.example.fox.ratusha.utils.CircleTransform


/**
 * @author Evgeny Butov
 * @created 17.02.2019
 */
class BaseRecyclerListAdapter(var itemList: MutableList<ItemCategory> = mutableListOf()) : RecyclerView.Adapter<BaseRecyclerListAdapter.BaseViewHolder>() {

    val clickItemSubject = PublishSubject.create<ItemClick<ItemCategory>>()

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
                if (itemList[position].id < 6) clickItemSubject.onNext(ItemClick(itemList[position], position))
                else itemView.context.startActivity(DetailItemInfo.getInstance(itemView.context, itemList[position].id))
            }
        }

        private fun inflateData(id: Int, itemName: String, image: String) {
            val resourceId = itemView.context?.resources?.getIdentifier(image, "drawable", itemView.context.packageName)
            if (resourceId != null) itemView.imageItemCategory.setImageResource(resourceId)
            itemView.nameItemCategory.text = itemName
        }
    }

}