package com.blackstone.ratusha.ui.screens.information

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvp.BaseMvpFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import kotlinx.android.synthetic.main.fragment_information.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FInformation : BaseMvpFragment<FInformationPresenter, ControllerRouter>(), FInformationView {
    override fun providePresenter(): FInformationPresenter = FInformationPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_information

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = presenter.adapter
    }

    fun onBackPresserFragment() {
        presenter.getCategoryDao()
    }
}