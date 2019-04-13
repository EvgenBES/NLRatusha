package com.blackstone.ratusha.ui.screens.information

import android.util.Log
import com.blackstone.domain.usecases.GetCategoryListUseCase
import com.blackstone.domain.usecases.GetItemsUseCase
import com.blackstone.ratusha.di.app.App
import com.blackstone.ratusha.ui.base.mvp.BasePresenter
import com.blackstone.ratusha.ui.base.recycler.RecyclerCategoryAdapter
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.ratusha.ui.screens.mainManager.MainRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 16.02.2019
 */
class FInformationPresenter(view: FInformationView) : BasePresenter<MainRouter, FInformationView>(view) {

    val adapter = RecyclerCategoryAdapter()


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
//                    router?.activity?.changedStatusRecyclerFragment(false)
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
//                        router?.activity?.changedStatusRecyclerFragment(true)
                    },
                    onError = { Log.d("AAQQ", "onClickItem message: ${it.message}") }
            )
            addToDisposible(disposable)
    }
}