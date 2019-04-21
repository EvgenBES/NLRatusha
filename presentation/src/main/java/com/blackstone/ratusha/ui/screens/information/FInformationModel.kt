package com.blackstone.ratusha.ui.screens.information

import android.util.Log
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.usecases.GetCategoryListUseCase
import com.blackstone.domain.usecases.GetItemsUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.base.recycler.RecyclerCategoryAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class FInformationModel: BaseViewModel<ControllerRouter>() {

    companion object {
        const val TAG = "Ratusha FInfoModel"
    }

    val adapter = RecyclerCategoryAdapter()

    @Inject
    lateinit var getCategoryList: GetCategoryListUseCase

    @Inject
    lateinit var getItemsUseCase: GetItemsUseCase

    init {
        App.appComponent.runInject(this)
        getCategoryDao()
        subscribeOnClick()
    }

    fun getCategoryDao() {
        val disposable = getCategoryList.getCategoryListOrder().subscribeBy(
            onNext = {
                adapter.setItems(it)
                    router?.activity?.changedStatusRecyclerFragment(false)
            },
            onError = { Log.d(TAG, "getCategoryDao message: ${it.message}") }
        )
        addToDisposable(disposable)
    }

    private fun subscribeOnClick() {
        val disposable = adapter.clickItemSubject.subscribeBy(
            onNext = { onClickItem(it) },
            onError = { Log.d(TAG, "subcribeOnClick message: ${it.message}") }
        )
        addToDisposable(disposable)
    }

    private fun onClickItem(item: ItemClick<ItemCategory>) {
        val disposable = getItemsUseCase.getCategoryListOrder(item.item.id).subscribeBy(
            onNext = {
                adapter.setItems(it)
                router?.activity?.changedStatusRecyclerFragment(true)
            },
            onError = { Log.d(TAG, "onClickItem message: ${it.message}") }
        )
        addToDisposable(disposable)
    }
}