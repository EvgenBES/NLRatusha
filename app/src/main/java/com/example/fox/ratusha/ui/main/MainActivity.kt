package com.example.fox.ratusha.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.service.LoadService
import com.example.fox.ratusha.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation()
    }

    override fun onStart() {
        super.onStart()
        startService(Intent(this, LoadService::class.java))
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    override fun setForpostInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String) {
        time_order_forp.text = timeOrder
        progress_order_forp.text = "$progressOrder%"
        product_time_forp.text = timeProduct
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
                .into(product_item_forp)
    }

    @SuppressLint("SetTextI18n")
    override fun setOctalInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String) {
        time_order_octal.text = timeOrder
        progress_order_octal.text = "$progressOrder%"
        product_time_octal.text = timeProduct
        Picasso.get()
                .load("http://image.neverlands.ru/weapon/$urlProduct")
                .placeholder(R.drawable.ic_hourglass)
                .error(R.drawable.ic_cancel)
                .into(product_item_octal)
    }

    fun bottomNavigation() {
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

}