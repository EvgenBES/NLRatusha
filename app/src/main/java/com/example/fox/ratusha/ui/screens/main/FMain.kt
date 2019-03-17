package com.example.fox.ratusha.ui.screens.main

import android.os.Bundle
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FMain : BaseMvpFragment<FMainPresenter, MainRouter>(), FMainView, View.OnClickListener {

    override fun providePresenter(): FMainPresenter = FMainPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_main

    private lateinit var onSwipeRefreshListener: OnRefreshInfoListener

    interface OnRefreshInfoListener {
        fun onRefresh()
        fun onClickForpost()
        fun onClickOctal()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onSwipeRefreshListener = activity as OnRefreshInfoListener

        setSwipeControler()
        initClick()
    }

    override fun setImageProductForpost(urlProduct: String) {
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
                .into(product_item_forp)
    }


    override fun setImageProductOctal(urlProduct: String) {
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
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
        if (product_time_forp != null && product_time_octal != null) {
            product_time_forp.text = time
            product_time_octal.text = time
        }
    }

    fun hideButtonRefresh() {
        buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    fun showButtonRefresh() {
        buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeControler() {
        swipeContainer.setOnRefreshListener {
            onSwipeRefreshListener.onRefresh()
            swipeContainer.isRefreshing = false
        }
    }

    private fun initClick() {
        buttonRefresh.setOnClickListener(this)
        firstCastle.setOnClickListener(this)
        secondCastle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            buttonRefresh -> onSwipeRefreshListener.onRefresh()
            firstCastle -> onSwipeRefreshListener.onClickForpost()
            secondCastle -> onSwipeRefreshListener.onClickOctal()
        }
    }

}