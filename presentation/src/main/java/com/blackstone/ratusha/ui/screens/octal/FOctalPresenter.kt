package com.blackstone.ratusha.ui.screens.octal

import android.util.Log
import com.blackstone.domain.usecases.GetItemOctalUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvp.BasePresenter
import com.blackstone.ratusha.ui.base.recycler.RecyclerItemRatushaAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FOctalPresenter (view: FOctalView): BasePresenter<ControllerRouter, FOctalView>(view) {
    val octalAdapter = RecyclerItemRatushaAdapter()

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