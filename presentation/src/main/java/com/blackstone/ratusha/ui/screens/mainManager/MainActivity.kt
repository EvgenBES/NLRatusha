package com.blackstone.ratusha.ui.screens.mainManager

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmActivity
import com.blackstone.ratusha.ui.screens.main.FMain
import com.blackstone.ratusha.databinding.ActivityMainBinding
import com.blackstone.ratusha.ui.screens.forpost.FForpost
import com.blackstone.ratusha.ui.screens.information.FInformation
import com.blackstone.ratusha.ui.screens.octal.FOctal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvvmActivity<MainViewModel, MainRouter, ActivityMainBinding>(), FMain.OnRefreshInfoListener {


    override fun provideRouter(): MainRouter = MainRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_main
    override fun prodiveViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)

    }
    private var selectedFragment: Fragment = FMain()
    private var timerBackPressed: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) router.startFragment(FMain())

        bottomNavigation()
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

//    override fun onBackPressed() {
//        val fragment: Fragment? = supportFragmentManager.findFragmentById(com.blackstone.ratusha.R.id.mainFragment)
//        if (presenter.stateRecyclerFragment && fragment != null && fragment is FInformation) (fragment).onBackPresserFragment()
//        else {
//            if (Calendar.getInstance().time.time - 1500 < timerBackPressed) {
//                finish()
//            } else {
//                timerBackPressed = Calendar.getInstance().time.time
//                Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    override fun onClickForpost() {
        navigation.menu.getItem(1).isChecked = true
        router.startFragment(FForpost())
    }

    override fun onClickOctal() {
        navigation.menu.getItem(2).isChecked = true
        router.startFragment(FOctal())
    }

    fun changedStatusRecyclerFragment(state: Boolean) {
//        presenter.stateRecyclerFragment = state
    }

    override fun onRefresh() {

    }
}