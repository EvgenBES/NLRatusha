package com.blackstone.ratusha.ui.screens.main

import android.os.Bundle
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvvm.BaseMvvmFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

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
    }

    fun hideButtonRefresh() {
        buttonRefresh.animate().alpha(0.0f).duration = 750
    }

    fun showButtonRefresh() {
        buttonRefresh.animate().alpha(1.0f).duration = 750
    }

    private fun setSwipeController() {
        swipeContainer.setOnRefreshListener {
            router?.refrashInformation()
            swipeContainer.isRefreshing = false
        }
    }

    private fun initClick() {
        buttonRefresh.setOnClickListener(this)
        firstCastle.setOnClickListener(this)
        secondCastle.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            buttonRefresh -> router?.refrashInformation()
            firstCastle -> onSwipeRefreshListener.onClickForpost()
            secondCastle -> onSwipeRefreshListener.onClickOctal()
        }
    }

}