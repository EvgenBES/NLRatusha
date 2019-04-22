package com.blackstone.ratusha.ui.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.databinding.FragmentMainBinding
import com.blackstone.ratusha.ui.screens.controller.ControllerModel

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FMain : BaseMvvmFragment<FMainModel, ControllerRouter, FragmentMainBinding>(), View.OnClickListener {

    override fun prodiveViewModel(): FMainModel = FMainModel()
    override fun provideLayoutId(): Int = R.layout.fragment_main

    private lateinit var onSwipeRefreshListener: OnRefreshInfoListener

    interface OnRefreshInfoListener {
        fun onClickForpost()
        fun onClickOctal()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onSwipeRefreshListener = activity as OnRefreshInfoListener

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

    private fun observeInput(controllerModel: ControllerModel) {
        controllerModel.stateData.observe(this, Observer { status ->
            status?.let { if (status) hideButtonRefresh() else showButtonRefresh() }
        })
    }


    fun hideButtonRefresh() {
        binding.buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    fun showButtonRefresh() {
        binding.buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeController() {
        binding.swipeContainer.setOnRefreshListener {
            router?.refrashInformation()
            binding.swipeContainer.isRefreshing = false
        }
    }

    private fun initClick() {
        binding.buttonRefresh.setOnClickListener(this)
        binding.firstCastle.setOnClickListener(this)
        binding.secondCastle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.buttonRefresh -> router?.refrashInformation()
            binding.firstCastle -> onSwipeRefreshListener.onClickForpost()
            binding.secondCastle -> onSwipeRefreshListener.onClickOctal()
        }
    }

}