package com.example.fox.ratusha.ui.screens.information

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetCategoryListUseCase
import com.example.fox.ratusha.data.usecases.GetItemsUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.BaseRecyclerListAdapter
import com.example.fox.ratusha.ui.base.recycler.ItemClick
import com.example.fox.ratusha.ui.entity.ItemCategory
import com.example.fox.ratusha.ui.screens.detailed.DetailItemInfo
import com.example.fox.ratusha.ui.screens.mainManager.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FInformationPresenter(view: FInformationView) : BasePresenter<MainRouter, FInformationView>(view) {

    val adapter = BaseRecyclerListAdapter()


    init {
        App.appComponent.runInject(this)
        getCategoryDao()
        subcribeOnClick()
    }

    @Inject
    lateinit var getCategoryList: GetCategoryListUseCase

    @Inject
    lateinit var getItemsUseCase: GetItemsUseCase

    fun getCategoryDao() {
        val disposable = getCategoryList.getCategoryListOrder().subscribeBy(
                onNext = {
                    adapter.setItems(it)
                    router?.activity?.changedStatusRecyclerFragment(false)
                },
                onError = { Log.d("AAQQ", "getCategoryDao message: ${it.message}") }
        )
        addToDisposible(disposable)
    }

    private fun subcribeOnClick() {
        val disposable = adapter.clickItemSubject.subscribeBy(
                onNext = { onClickItem(it) },
                onError = { Log.d("AAQQ", "subcribeOnClick message: ${it.message}") }
        )
        addToDisposible(disposable)
    }

    private fun onClickItem(item: ItemClick<ItemCategory>) {
            val disposable = getItemsUseCase.getCategoryListOrder(item.item.id).subscribeBy(
                    onNext = {
                        adapter.setItems(it)
                        router?.activity?.changedStatusRecyclerFragment(true)
                    },
                    onError = { Log.d("AAQQ", "onClickItem message: ${it.message}") }
            )
            addToDisposible(disposable)
    }
}