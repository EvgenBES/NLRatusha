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
        item_contain_wrapper.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
        item_contain_wrapper.visibility = View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    override fun setForpostInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String){
        time_order_forp.text = timeOrder
        progress_order_forp.text = "$progressOrder%"
        Picasso.get().load("http://image.neverlands.ru/weapon/$urlProduct").placeholder(R.drawable.ic_hourglass).error(R.drawable.ic_cancel).into(product_item_forp)
        product_time_forp.text = timeProduct
    }

    @SuppressLint("SetTextI18n")
    override fun setOctalInfo(timeOrder: String, progressOrder: Int, urlProduct: String, timeProduct: String){
        time_order_octal.text = timeOrder
        progress_order_octal.text = "$progressOrder%"
        Picasso.get().load("http://image.neverlands.ru/weapon/$urlProduct").placeholder(R.drawable.ic_hourglass).error(R.drawable.ic_cancel).into(product_item_octal)
        product_time_octal.text = timeProduct
    }

    fun bottomNavigation(){
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> return@OnNavigationItemSelectedListener true
                R.id.navigation_list -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_alert -> return@OnNavigationItemSelectedListener true
            }
            false
        }
    }

}