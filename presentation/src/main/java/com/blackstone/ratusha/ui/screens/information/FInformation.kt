package com.blackstone.ratusha.ui.screens.information

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.databinding.FragmentInformationBinding
import com.blackstone.ratusha.ui.base.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FInformation : BaseMvvmFragment<FInformationModel, ControllerRouter, FragmentInformationBinding>() {

    override fun provideLayoutId(): Int = R.layout.fragment_information
    override fun provideViewModel(): FInformationModel {
        return ViewModelProviders.of(this).get(FInformationModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = viewModel.adapter
    }

    fun onBackPresserFragment() {
        viewModel.getCategoryDao()
    }
}