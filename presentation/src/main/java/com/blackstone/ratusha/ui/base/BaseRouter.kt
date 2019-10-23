package com.blackstone.ratusha.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        tag: String,
        containerResId: Int,
        addToBackStack: Boolean = false
    ) {
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, tag)
        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }
        fragmentTransition.commit()
    }
}