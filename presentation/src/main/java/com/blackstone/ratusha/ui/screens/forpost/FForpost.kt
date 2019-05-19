package com.blackstone.ratusha.ui.screens.forpost

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.FragmentForpostBinding
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FForpost : BaseMvvmFragment<FForpostModel, ControllerRouter, FragmentForpostBinding>(){

    override fun provideLayoutId(): Int = R.layout.fragment_forpost
    override fun provideViewModel(): FForpostModel {
        return ViewModelProviders.of(this).get(FForpostModel::class.java)
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