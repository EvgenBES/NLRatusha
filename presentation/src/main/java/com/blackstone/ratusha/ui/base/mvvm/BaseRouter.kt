package com.blackstone.ratusha.ui.base.mvvm

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast

abstract class BaseRouter<A : BaseActivity>(val activity: A) {

    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun replaceFragment(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            containerResId: Int,
            addToBackStack: Boolean = false
    ) {
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, fragment::class.java.simpleName)
        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }
        fragmentTransition.commit()
    }
}