package com.example.fox.ratusha.ui.screens.mainManager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpActivity
import com.example.fox.ratusha.ui.screens.forpost.FForpost
import com.example.fox.ratusha.ui.screens.information.FInformation
import com.example.fox.ratusha.ui.screens.main.FMain
import com.example.fox.ratusha.ui.screens.octal.FOctal
import com.example.fox.ratusha.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseMvpActivity<MainPresenter, MainRouter, ActivityMainBinding>(), MainView, FMain.OnRefreshInfoListener {

    override fun providePresenter(): MainPresenter = MainPresenter(this)
    override fun provideRouter(): MainRouter = MainRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_main

    private var selectedFragment: Fragment = FMain()
    private var timerBackPressed: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) router.startFragment(FMain())
//            supportFragmentManager.beginTransaction().add(R.id.mainFragment, ).commit()

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
            router.startFragment(selectedFragment)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(com.example.fox.ratusha.R.id.mainFragment)
        if (presenter.stateRecyclerFragment && fragment != null && fragment is FInformation) (fragment).onBackPresserFragment()

        else {
            if (Calendar.getInstance().time.time - 1500 < timerBackPressed) {
                finish()
            } else {
                timerBackPressed = Calendar.getInstance().time.time
                Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRefresh() {
        presenter.getOrderInformation()
    }

    override fun onClickForpost() {
        navigation.menu.getItem(1).isChecked = true
        router.startFragment(FForpost())
    }

    override fun onClickOctal() {
        navigation.menu.getItem(2).isChecked = true
        router.startFragment(FOctal())
    }

    fun changedStatusRecyclerFragment(state: Boolean) {
        presenter.stateRecyclerFragment = state
    }
}