package com.example.fox.ratusha.ui.screens.forpost

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import kotlinx.android.synthetic.main.fragment_forpost.*

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FForpost : BaseMvpFragment<FForpostPresenter, MainRouter>(), FForpostView {

    override fun providePresenter(): FForpostPresenter = FForpostPresenter(this)
    override fun provideLayoutId(): Int = R.layout.fragment_forpost

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerview.layoutManager = LinearLayoutManager(this.activity)
        recyclerview.setHasFixedSize(true)
//        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = presenter.forpostAdapter

    }

    override fun setTimerOrder(time: String) {
        time_order_forp.text = time
    }

}