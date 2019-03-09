package com.example.fox.ratusha.ui.screens.information

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import kotlinx.android.synthetic.main.fragment_information.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FInformation : BaseMvpFragment<FInformationPresenter, MainRouter>(), FInformationView {
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