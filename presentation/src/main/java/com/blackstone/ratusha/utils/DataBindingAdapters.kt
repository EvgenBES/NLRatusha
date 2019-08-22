package com.blackstone.ratusha.utils

import android.text.Spanned
import android.util.Log
import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blackstone.ratusha.ui.widget.ExpandableCardView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_order.view.*
import kotlinx.android.synthetic.main.view_expandable_card.view.*


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
        Picasso.get()
            .load("http://image.neverlands.ru/weapon/$url")
            .placeholder(com.blackstone.ratusha.R.drawable.ic_hourglass)
            .error(com.blackstone.ratusha.R.drawable.ic_cancel)
            .into(view)
}

@BindingAdapter("app:src")
fun setImageResource(view: ImageView, resource: Int) {
    if (resource > 0) view.setImageResource(resource)
}

@BindingAdapter("app:src")
fun setImageResource(view: ImageView, image: String) {
    val imageRes = view.context?.resources?.getIdentifier("ic_$image", "drawable", view.context.packageName)
    imageRes?.let { if (it > 0) view.setImageResource(imageRes) }

}


@BindingAdapter("app:progress")
fun setLayoutHeight(view: View, width: Int) {
    val layoutParams = view.layoutParams
    layoutParams.width = width
    view.layoutParams = layoutParams
}


@BindingAdapter("visibility")
fun View.visidility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}


@BindingAdapter("textResource")
fun setTextResource(view: TextView, resource: Int) {
    if (resource > 0) view.text = view.resources.getString(resource)
}


/** ExpandableCardView **/

@BindingAdapter("app:title")
fun setTitleCardView(view: ExpandableCardView, title: String) {
    view.exp_cardview.cardTitle = title
}

@BindingAdapter("app:total")
fun setTotalCardView(view: ExpandableCardView, total: String) {
    view.exp_cardview.cardTotal = total
}

@BindingAdapter("app:image")
fun setImageCardView(view: ExpandableCardView, image: String) {
    val imageRes = view.context?.resources?.getIdentifier(image, "drawable", view.context.packageName)
    imageRes?.let { if (it > 0) view.exp_cardview.cardImage = imageRes }
}

@BindingAdapter("app:price")
fun setPriceCardView(view: ExpandableCardView, price: String) {
    view.exp_cardview.cardPrice = price
}

@BindingAdapter("app:progress")
fun setProgressCardView(view: ExpandableCardView, progress: Int) {
    view.exp_cardview.cardProgress = progress
}

@BindingAdapter("app:quantity")
fun setQuantityCardView(view: ExpandableCardView, quantity: Spanned) {
    view.exp_cardview.cardQuantity = quantity
}

@BindingAdapter("app:remainder")
fun setRemainderCardView(view: ExpandableCardView, remainder: String) {
    view.exp_cardview.cardRemainder = remainder
}

@BindingAdapter("app:totalRemain")
fun setTotalRemainCardView(view: ExpandableCardView, totalRemain: String) {
    view.exp_cardview.cardTotalRemain = totalRemain
}
