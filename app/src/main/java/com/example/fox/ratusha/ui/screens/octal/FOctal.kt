package com.example.fox.ratusha.ui.screens.octal

import android.os.Bundle
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter

/**
 * @author Evgeny Butov
 * @since 16.02.2019
 */
class FOctal : BaseMvpFragment<FOctalPresenter, MainRouter>(), FOctalView {

    override fun providePresenter(): FOctalPresenter = FOctalPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_octal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}