package com.example.fox.ratusha.ui.screens.forpost

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.entity.TotalSum
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

        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this.activity)
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = presenter.forpostAdapter
    }

    override fun setTimerOrder(time: String) {
        time_order_forp.text = time
    }

    @SuppressLint("SetTextI18n")
    override fun setTotalSum(totalSum: TotalSum) {
        tv_total.text = "Сумма: ${totalSum.total}"
        tv_paid.text = "Залито: ${totalSum.paid} [${totalSum.paidPercent}%]"
        tv_remainder.text = "Осталось: ${totalSum.remainder} [${totalSum.remainderPercent}%]"
    }

}