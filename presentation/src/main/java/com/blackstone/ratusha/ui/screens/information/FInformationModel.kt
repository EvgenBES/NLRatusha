package com.blackstone.ratusha.ui.screens.information

import android.util.Log
import androidx.lifecycle.Observer
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.usecases.GetCategoryListUseCase
import com.blackstone.domain.usecases.GetListItemCategoryUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.adapter.RecyclerCategoryAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.ui.screens.detailed.DetailItemFragment
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class FInformationModel : BaseViewModel<ControllerRouter>() {

    val adapter = RecyclerCategoryAdapter()

    @Inject lateinit var getCategoryList: GetCategoryListUseCase
    @Inject lateinit var getListItemCategoryUseCase: GetListItemCategoryUseCase

    private val onClickAdapter: Observer<ItemClick<ItemCategory>> = Observer { onClickItem(it) }
    private val onStartDetail: Observer<ItemClick<ItemCategory>> = Observer { startFragment(it) }

    init {
        App.appComponent.runInject(this)
        getCategoryDao()
        adapter.clickItemSubject.observeForever(onClickAdapter)
        adapter.clickDetailItemInfo.observeForever(onStartDetail)
    }

    override fun onCleared() {
        adapter.clickItemSubject.removeObserver(onClickAdapter)
        adapter.clickDetailItemInfo.removeObserver(onStartDetail)
        super.onCleared()
    }

    fun getCategoryDao() {
        getCategoryList.execute {
            onComplete {
                adapter.setItems(it)
                router?.activity?.changedStatusRecyclerFragment(false)
            }
            onError { Log.d(TAG, "getCategoryDao message: ${it.message}") }
        }
    }

    private fun onClickItem(item: ItemClick<ItemCategory>) {
        getListItemCategoryUseCase.execute(item.item.id) {
            onComplete {
                adapter.setItems(it)
                router?.activity?.changedStatusRecyclerFragment(true)
            }
            onError { Log.d(TAG, "onClickItem message: ${it.message}") }
        }
    }

    private fun startFragment(item: ItemClick<ItemCategory>) {
        router?.startFragmentTest(DetailItemFragment())
    }
}