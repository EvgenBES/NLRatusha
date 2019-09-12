package com.blackstone.ratusha.ui.screens.main

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.databinding.FragmentMainBinding
import com.blackstone.ratusha.ui.screens.controller.ControllerModel
import com.blackstone.ratusha.utils.BitmapHelper

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FMain : BaseMvvmFragment<FMainModel, ControllerRouter, FragmentMainBinding>(), View.OnClickListener {

    override fun provideViewModel(): FMainModel = FMainModel()
    override fun provideLayoutId(): Int = R.layout.fragment_main

    private lateinit var onSwipeRefreshListener: OnRefreshInfoListener

    interface OnRefreshInfoListener {
        fun onClickForpost()
        fun onClickOctal()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onSwipeRefreshListener = activity as OnRefreshInfoListener

        initImage(context)
        setSwipeController()
        initClick()

        /**
         *  create view model in activity scope
         */
        activity?.let {
            val sharedViewModel = ViewModelProviders.of(it).get(ControllerModel::class.java)
            observeInput(sharedViewModel)
        }
    }

    private fun initImage(context: Context?){
        context?.let {
           binding.imgForpost.setImageBitmap(BitmapHelper.setCornersRadius(it, R.drawable.bg_forp))
           binding.imgOctal.setImageBitmap(BitmapHelper.setCornersRadius(it, R.drawable.bg_octal))
        }
    }

    private fun observeInput(controllerModel: ControllerModel) {
        controllerModel.getButtonRefresh().observe(this, Observer { status ->
            status?.let { if (status) hideButtonRefresh() else showButtonRefresh() }
        })

        controllerModel.getProgressBar().observe(this, Observer { status ->
            status?.let { if (status) showProgressBar() else hideProgressBar()  }
        })
    }


    private fun hideButtonRefresh() {
        binding.buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    private fun showButtonRefresh() {
        binding.buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun hideProgressBar() {
        binding.progressBar.animate().alpha(0.0f).duration = 750
    }

    private fun showProgressBar() {
        binding.progressBar.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeController() {
        binding.swipeContainer.setOnRefreshListener {
            router?.refreshInformation()
            binding.swipeContainer.isRefreshing = false
        }
    }

    private fun initClick() {
        binding.buttonRefresh.setOnClickListener(this)
        binding.imgForpost.setOnClickListener(this)
        binding.imgOctal.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.buttonRefresh -> router?.refreshInformation()
            binding.imgForpost -> onSwipeRefreshListener.onClickForpost()
            binding.imgOctal -> onSwipeRefreshListener.onClickOctal()
        }
    }
}