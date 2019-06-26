package com.blackstone.ratusha.ui.screens.octal

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.FragmentOctalBinding
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FOctal : BaseMvvmFragment<FOctalModel, ControllerRouter, FragmentOctalBinding>(){

    override fun provideLayoutId(): Int = R.layout.fragment_octal
    override fun provideViewModel(): FOctalModel {
        return ViewModelProviders.of(this).get(FOctalModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerview.adapter = viewModel.adapter

        setSwipeController()
    }

    private fun setSwipeController() {
        binding.swipeContainer.setOnRefreshListener {
            router?.refreshInformation()
            binding.swipeContainer.isRefreshing = false
        }
    }

}