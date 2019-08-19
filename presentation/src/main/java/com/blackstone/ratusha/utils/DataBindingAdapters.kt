package com.blackstone.ratusha.utils

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


@BindingAdapter("first")
fun setFirstImage(view: ImageView, urlFirst: String?) {
        Picasso.get()
            .load("http://image.neverlands.ru/weapon/$urlFirst")
            .placeholder(com.blackstone.ratusha.R.drawable.ic_hourglass)
            .error(com.blackstone.ratusha.R.drawable.ic_cancel)
            .into(view)
}

@BindingAdapter("second")
fun setSecondImage(view: ImageView, urlSecond: String?) {
        Picasso.get()
            .load("http://image.neverlands.ru/weapon/$urlSecond")
            .placeholder(com.blackstone.ratusha.R.drawable.ic_hourglass)
            .error(com.blackstone.ratusha.R.drawable.ic_cancel)
            .into(view)
}

@BindingAdapter("app:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
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

