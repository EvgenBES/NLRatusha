package com.blackstone.ratusha.utils

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


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
