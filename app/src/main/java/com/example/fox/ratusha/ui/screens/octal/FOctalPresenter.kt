package com.example.fox.ratusha.ui.screens.octal

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetItemForpostUseCase
import com.example.fox.ratusha.data.usecases.GetItemOctalUseCase
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
class FOctalPresenter (view: FOctalView): BasePresenter<MainRouter, FOctalView>(view) {
    val octalAdapter = BaseRecyclerAdapter()

    @Inject
    lateinit var getItemOctal: GetItemOctalUseCase

    init {
        App.appComponent.runInject(this)

        getItem()
    }

    private fun getItem() {
        val disposable = getItemOctal.getAllItemOrder()
                .subscribeBy(
                        onNext = { octalAdapter.setItems(it) },
                        onError = { Log.d("AAQQ", "Error message: ${it.message}") }
                )
        addToDisposible(disposable)
    }

}