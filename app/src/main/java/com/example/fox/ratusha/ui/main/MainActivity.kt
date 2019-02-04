package com.example.fox.ratusha.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainPresenter, MainRouter>(), MainView {

    override fun providePresenter(): MainPresenter =  MainPresenter(this)
    override fun provideRouter(): MainRouter = MainRouter(this)
    override fun provideLayoutId(): Int =  R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bottomNavigation()
        buttonRefreshClick()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }


    override fun setForpostInfo(timeOrder: String, progressOrder: String, urlProduct: String, timeProduct: String) {
        time_order_forp.text = timeOrder
        progress_order_forp.text = progressOrder
        product_time_forp.text = timeProduct
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
                .into(product_item_forp)
    }


    override fun setOctalInfo(timeOrder: String, progressOrder: String, urlProduct: String, timeProduct: String) {
        time_order_octal.text = timeOrder
        progress_order_octal.text = progressOrder
        product_time_octal.text = timeProduct
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
                .into(product_item_octal)
    }

    private fun bottomNavigation() {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_btn_home -> return@OnNavigationItemSelectedListener true
                R.id.menu_btn_fp -> return@OnNavigationItemSelectedListener true
                R.id.menu_btn_oct -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_btn_info -> return@OnNavigationItemSelectedListener true
            }
            false
        }
    }

    private fun buttonRefreshClick() {
        buttonRefresh.setOnClickListener { presenter.setItem() }

    }

}