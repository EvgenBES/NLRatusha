package com.blackstone.ratusha.utils

import android.text.Spanned
import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blackstone.ratusha.ui.widget.ExpandableCardView
import kotlinx.android.synthetic.main.item_order.view.*

@BindingAdapter("src")
fun setImageResource(view: ImageView, resource: Int) {
    if (resource > 0) view.setImageResource(resource)
}

@BindingAdapter("src")
fun setImageResource(view: ImageView, image: String?) {
    val imageRes = view.context?.resources?.getIdentifier("ic_$image", "drawable", view.context.packageName)
    imageRes?.let { if (it > 0) view.setImageResource(imageRes) }
}


@BindingAdapter("progress")
fun setLayoutWidth(view: View, width: Int) {
    val layoutParams = view.layoutParams
    layoutParams.width = width
    view.layoutParams = layoutParams
}


@BindingAdapter("visibility")
fun View.visibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("anim")
fun visibilityAnim(view: View, visibility: Boolean) {
    if (visibility) {
        view.animate().alpha(1.0f).duration = 1250
    } else {
        view.animate().alpha(0.0f).duration = 1250
    }
}


@BindingAdapter("textResource")
fun setTextResource(view: TextView, resource: Int) {
    if (resource > 0) view.text = view.resources.getString(resource)
}


/** ExpandableCardView **/

@BindingAdapter("title")
fun setTitleCardView(view: ExpandableCardView, title: String) {
    view.exp_cardview.cardTitle = title
}

@BindingAdapter("total")
fun setTotalCardView(view: ExpandableCardView, total: String) {
    view.exp_cardview.cardTotal = total
}

@BindingAdapter("image")
fun setImageCardView(view: ExpandableCardView, image: String) {
    val imageRes = view.context?.resources?.getIdentifier(image, "drawable", view.context.packageName)
    imageRes?.let { if (it > 0) view.exp_cardview.cardImage = imageRes }
}

@BindingAdapter("price")
fun setPriceCardView(view: ExpandableCardView, price: String) {
    view.exp_cardview.cardPrice = price
}

@BindingAdapter("progress")
fun setProgressCardView(view: ExpandableCardView, progress: Int) {
    view.exp_cardview.cardProgress = progress
}

@BindingAdapter("quantity")
fun setQuantityCardView(view: ExpandableCardView, quantity: Spanned) {
    view.exp_cardview.cardQuantity = quantity
}

@BindingAdapter("remainder")
fun setRemainderCardView(view: ExpandableCardView, remainder: String) {
    view.exp_cardview.cardRemainder = remainder
}

@BindingAdapter("totalRemain")
fun setTotalRemainCardView(view: ExpandableCardView, totalRemain: String) {
    view.exp_cardview.cardTotalRemain = totalRemain
}
