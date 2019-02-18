package com.example.fox.ratusha.ui.screens.forpost

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.fox.ratusha.R
import com.example.fox.ratusha.ui.base.BaseMvpFragment
import com.example.fox.ratusha.ui.base.recycler.BaseRecyclerAdapter
import com.example.fox.ratusha.ui.entity.ItemOrder
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


        val list = mutableListOf<ItemOrder>()
        list.add(ItemOrder(1, "2", "3", 4, 5))
        list.add(ItemOrder(2, "2", "3", 4, 5))
        list.add(ItemOrder(3, "2", "3", 4, 5))
        list.add(ItemOrder(3, "2", "3", 4, 5))
        list.add(ItemOrder(3, "2", "3", 4, 5))
        list.add(ItemOrder(3, "2", "3", 4, 5))
        list.add(ItemOrder(3, "2", "3", 4, 5))

        val forpostAdapter = BaseRecyclerAdapter()
        forpostAdapter.setItems(list)

        val layoutManager = GridLayoutManager(this.activity, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerview.layoutManager = layoutManager
        recyclerview.setHasFixedSize(true)
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = forpostAdapter

    }

}