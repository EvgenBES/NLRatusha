package com.blackstone.ratusha.ui.screens.controller

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.ActivityControllerBinding
import com.blackstone.ratusha.ui.Screens
import com.blackstone.ratusha.ui.Screens.FORPOST
import com.blackstone.ratusha.ui.Screens.HOME
import com.blackstone.ratusha.ui.Screens.INFO
import com.blackstone.ratusha.ui.Screens.OCTAL
import com.blackstone.ratusha.ui.base.BaseMvvmActivity
import com.blackstone.ratusha.ui.screens.detailed.DetailItemFragment
import com.blackstone.ratusha.ui.screens.forpost.FForpost
import com.blackstone.ratusha.ui.screens.information.FInformation
import com.blackstone.ratusha.ui.screens.main.FMain
import com.blackstone.ratusha.ui.screens.octal.FOctal
import com.blackstone.ratusha.utils.DisplayUtils
import java.util.*

class ControllerActivity : BaseMvvmActivity<ControllerModel, ControllerRouter, ActivityControllerBinding>(), FMain.OnRefreshInfoListener {

    override fun provideRouter(): ControllerRouter = ControllerRouter(this)
    override fun provideLayoutId(): Int = R.layout.activity_controller
    override fun provideViewModel(): ControllerModel {
        return ViewModelProviders.of(this).get(ControllerModel::class.java)
    }

    private var timerBackPressed: Long = 0L

    private val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.constraintLayout2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)  selectTab(HOME)
        bottomNavigation()
    }


    private fun bottomNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener { item ->
            setBaseStyleNavigation()
            when (item.itemId) {
                R.id.menu_btn_home -> selectTab(HOME)
                R.id.menu_btn_fp -> selectTab(FORPOST)
                R.id.menu_btn_oct -> { selectTab(OCTAL)
                    setYellowStyleNavigation()
                }
                R.id.menu_btn_info -> selectTab(INFO)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun selectTab(tab: String) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments

        if (fragments != null) {
            for (f: Fragment in fragments) {
                if (f.isVisible) {
                    currentFragment = f
                    break
                }
            }
        }

        val newFragment: Fragment? = fm.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        val transaction: FragmentTransaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(R.id.mainFragment, Screens.tabScreen(tab), tab)
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    private fun setBaseStyleNavigation() {
        binding.navigation.itemBackground = ResourcesCompat.getDrawable(resources, R.drawable.navigation_bg, null)
        binding.navigation.itemTextColor = DisplayUtils.getCommonColorNavigation()
        binding.navigation.itemIconTintList = DisplayUtils.getCommonColorNavigation()
    }

    private fun setYellowStyleNavigation() {
        binding.navigation.itemBackground = ResourcesCompat.getDrawable(resources, R.drawable.navigation_bg_yellow, null)
        binding.navigation.itemTextColor = DisplayUtils.getYellowStatesNavigation()
        binding.navigation.itemIconTintList = DisplayUtils.getYellowStatesNavigation()
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.mainFragment)
        if (currentFragment is DetailItemFragment) super.onBackPressed()
        else {
            if (viewModel.stateRecyclerFragment && fragment != null && fragment is FInformation) (fragment).onBackPresserFragment()
            else {
                if (Calendar.getInstance().time.time - 1500 < timerBackPressed) {
                    finish()
                } else {
                    timerBackPressed = Calendar.getInstance().time.time
                    Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClickForpost() {
        setBaseStyleNavigation()
        binding.navigation.menu.getItem(1).isChecked = true
        router.startFragment(FForpost())
    }

    override fun onClickOctal() {
        setYellowStyleNavigation()
        binding.navigation.menu.getItem(2).isChecked = true
        router.startFragment(FOctal())
    }

    fun changedStatusRecyclerFragment(state: Boolean) {
        viewModel.stateRecyclerFragment = state
    }

}