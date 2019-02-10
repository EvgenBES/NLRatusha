package com.example.fox.ratusha.ui.screens.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.example.fox.ratusha.ui.base.BaseMvpActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseMvpActivity<MainPresenter, MainRouter>(), MainView {

    override fun providePresenter(): MainPresenter = MainPresenter(this)
    override fun provideRouter(): MainRouter = MainRouter(this)
    override fun provideLayoutId(): Int = com.example.fox.ratusha.R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottomNavigation()
        buttonRefreshClick()
        setSwipeControler()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
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

    fun setForpostTime(time: String) {
        time_order_forp.text = time
    }

    fun setForpostProgress(progress: String) {
        progress_order_forp.text = progress
    }

    fun setOctalTime(time: String) {
        time_order_octal.text = time
    }

    fun setOctalProgress(progress: String) {
        progress_order_octal.text = progress
    }

    fun setTimeProduct(time: String) {
        product_time_forp.text = time
        product_time_octal.text = time
    }

    private fun bottomNavigation() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                com.example.fox.ratusha.R.id.menu_btn_home -> return@OnNavigationItemSelectedListener true
                com.example.fox.ratusha.R.id.menu_btn_fp -> return@OnNavigationItemSelectedListener true
                com.example.fox.ratusha.R.id.menu_btn_oct -> {
                    return@OnNavigationItemSelectedListener true
                }
                com.example.fox.ratusha.R.id.menu_btn_info -> return@OnNavigationItemSelectedListener true
            }
            false
        }
    }

    private fun buttonRefreshClick() {
        buttonRefresh.setOnClickListener { presenter.getOrderInformation() }
    }

    fun hideButtonRefresh() {
        buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    fun visibleButtonRefresh() {
        buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeControler() {
        swipeContainer.setOnRefreshListener{
            presenter.getOrderInformation()
            swipeContainer.isRefreshing = false
        }
    }

}