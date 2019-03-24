package com.example.fox.ratusha.ui.screens.octal

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import kotlinx.android.synthetic.main.fragment_octal.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FOctal : BaseMvpFragment<FOctalPresenter, MainRouter>(), FOctalView {

    override fun providePresenter(): FOctalPresenter = FOctalPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_octal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview.layoutManager = LinearLayoutManager(this.activity)
        recyclerview.setHasFixedSize(true)
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = presenter.octalAdapter

    }

}