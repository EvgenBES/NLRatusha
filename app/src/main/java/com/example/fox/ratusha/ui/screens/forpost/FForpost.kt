package com.example.fox.ratusha.ui.screens.forpost

import android.os.Bundle
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
class FForpost : BaseMvpFragment<FForpostPresenter, MainRouter>(), FForpostView {

    override fun providePresenter(): FForpostPresenter = FForpostPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_forpost

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}