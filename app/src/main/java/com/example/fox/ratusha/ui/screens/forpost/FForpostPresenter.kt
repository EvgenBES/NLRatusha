package com.example.fox.ratusha.ui.screens.forpost

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetItemForpostUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.BaseRecyclerAdapter
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FForpostPresenter(view: FForpostView) : BasePresenter<MainRouter, FForpostView>(view) {

    val forpostAdapter = BaseRecyclerAdapter()

    @Inject
    lateinit var getItemForpost: GetItemForpostUseCase

    init {
        App.appComponent.runInject(this)

        getItem()
    }

    private fun getItem() {
        val disposable = getItemForpost.getAllItemOrder()
                .subscribeBy(
                        onNext = { forpostAdapter.setItems(it) },
                        onError = { Log.d("AAQQ", "Error message: ${it.message}") }
                )
        addToDisposible(disposable)
    }

}