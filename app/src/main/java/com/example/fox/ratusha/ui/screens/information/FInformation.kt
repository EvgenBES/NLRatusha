package com.example.fox.ratusha.ui.screens.information

import android.os.Bundle
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
class FInformation : BaseMvpFragment<FInformationPresenter, MainRouter>(), FInformationView {

    override fun providePresenter(): FInformationPresenter = FInformationPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_information

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}