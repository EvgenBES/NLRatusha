package com.blackstone.ratusha.ui.screens.octal

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blackstone.ratusha.R
import com.blackstone.ratusha.ui.base.mvp.BaseMvpFragment
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import kotlinx.android.synthetic.main.fragment_octal.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FOctal : BaseMvpFragment<FOctalPresenter, ControllerRouter>(), FOctalView {

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