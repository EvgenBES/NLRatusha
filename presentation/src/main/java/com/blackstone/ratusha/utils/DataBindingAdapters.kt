package com.blackstone.ratusha.utils

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.blackstone.ratusha.R
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject

@BindingAdapter("first")
fun setFirstImage(view: ImageView, urlFirst: String?) {
        Picasso.get()
            .load("http://image.neverlands.ru/weapon/$urlFirst")
            .placeholder(R.drawable.ic_hourglass)
            .error(R.drawable.ic_cancel)
            .into(view)
}

@BindingAdapter("second")
fun setSecondImage(view: ImageView, urlSecond: String?) {
        Picasso.get()
            .load("http://image.neverlands.ru/weapon/$urlSecond")
            .placeholder(R.drawable.ic_hourglass)
            .error(R.drawable.ic_cancel)
            .into(view)
}

@BindingAdapter("onClick")
fun View.onClick(subject: PublishSubject<Boolean>) {
    setOnClickListener {
        subject.onNext(true)
    }
}


@BindingAdapter("visibility")
fun View.visidility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

