package com.example.fox.ratusha.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.fox.ratusha.R
import com.example.fox.ratusha.service.LoadService

class MainActivity : AppCompatActivity(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mOnNavigationItemSelectedListener =
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> return@OnNavigationItemSelectedListener true
                        R.id.navigation_list -> {return@OnNavigationItemSelectedListener true}
                        R.id.navigation_alert -> return@OnNavigationItemSelectedListener true
                    }
                    false
                }


        startService(Intent(this, LoadService::class.java))
    }


    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}