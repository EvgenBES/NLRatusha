package com.example.fox.ratusha.ui.base

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
            fragment: BaseFragment,
            containerResId: Int,
            addToBackStack: Boolean = false
    ) {
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, fragment::class.java.canonicalName)
        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }
        fragmentTransition.commit()
    }
}