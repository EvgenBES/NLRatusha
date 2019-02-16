package com.example.fox.ratusha.ui.screens.main

import android.os.Bundle
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
class FMain : BaseMvpFragment<FMainPresenter, MainRouter>(), FMainView {
    override fun providePresenter(): FMainPresenter = FMainPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_main

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonRefreshClick()
        setSwipeControler()
    }

    override fun setImageProductForpost(urlProduct: String) {
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(com.example.fox.ratusha.R.drawable.ic_hourglass)
                .error(com.example.fox.ratusha.R.drawable.ic_cancel)
                .into(product_item_forp)
    }


    override fun setImageProductOctal(urlProduct: String) {
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(com.example.fox.ratusha.R.drawable.ic_hourglass)
                .error(com.example.fox.ratusha.R.drawable.ic_cancel)
                .into(product_item_octal)
    }

    override fun setForpostTime(time: String) {
        time_order_forp.text = time
    }

    override fun setForpostProgress(progress: String) {
        progress_order_forp.text = progress
    }

    override fun setOctalTime(time: String) {
        time_order_octal.text = time
    }

    override fun setOctalProgress(progress: String) {
        progress_order_octal.text = progress
    }

    override fun setTimeProduct(time: String) {
        product_time_forp.text = time
        product_time_octal.text = time
    }

    private fun buttonRefreshClick() {
        buttonRefresh.setOnClickListener { router?.getOrderInformation() }
    }

    fun hideButtonRefresh() {
        buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    fun visibleButtonRefresh() {
        buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeControler() {
        swipeContainer.setOnRefreshListener{
//            router?.getOrderInformation() //TODO bag need fix
            swipeContainer.isRefreshing = false
        }
    }
}