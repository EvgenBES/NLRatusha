package com.example.fox.ratusha.ui.screens.mainManager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity
import com.example.fox.ratusha.ui.screens.forpost.FForpost
import com.example.fox.ratusha.ui.screens.information.FInformation
import com.example.fox.ratusha.ui.screens.main.FMain
import com.example.fox.ratusha.ui.screens.octal.FOctal
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseMvpActivity<MainPresenter, MainRouter>(), MainView, FMain.OnRefreshInfoListener {

    override fun providePresenter(): MainPresenter = MainPresenter(this)
    override fun provideRouter(): MainRouter = MainRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_main

    private var selectedFragment: Fragment = FMain()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainFragment, FMain()).commit()
        }

        bottomNavigation()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private fun bottomNavigation() {
        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_btn_home -> selectedFragment = FMain()
                R.id.menu_btn_fp -> selectedFragment = FForpost()
                R.id.menu_btn_oct -> selectedFragment = FOctal()
                R.id.menu_btn_info -> selectedFragment = FInformation()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragment, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true
        }
    }


    override fun onRefresh() {
        presenter.getOrderInformation()
    }

}