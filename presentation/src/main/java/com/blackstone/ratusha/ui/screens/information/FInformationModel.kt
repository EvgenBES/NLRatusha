package com.blackstone.ratusha.ui.screens.information

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.blackstone.domain.entity.ItemCategory
import com.blackstone.domain.usecases.category.GetCategoryListUseCase
import com.blackstone.domain.usecases.category.GetListItemCategoryUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.ItemClick
import com.blackstone.ratusha.ui.adapter.category.CategoryAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.ui.screens.detailed.DetailItemFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 20.04.2019
 */
class FInformationModel : BaseViewModel<ControllerRouter>() {

    private val adapter = CategoryAdapter()
    private val onClickAdapter: Observer<ItemClick<ItemCategory>> = Observer { onClickItem(it) }

    @Inject lateinit var getCategoryList: GetCategoryListUseCase
    @Inject lateinit var getListItemCategoryUseCase: GetListItemCategoryUseCase

    init {
        App.appComponent.runInject(this)
        getCategoryDao()
        adapter.onClickItemSubject().observeForever(onClickAdapter)
    }

    override fun onCleared() {
        adapter.onClickItemSubject().removeObserver(onClickAdapter)
        super.onCleared()
    }

    fun getCategoryAdapter(): CategoryAdapter = adapter

    fun getCategoryDao() {
        viewModelScope.launch {
            getCategoryList.execute {
                onComplete {
                    adapter.setItems(it)
                    router?.activity?.changedStatusRecyclerFragment(false)
                }
                onError { Log.d(TAG, "getCategoryDao message: ${it.message}") }
            }
        }
    }

    private fun onClickItem(itemClick: ItemClick<ItemCategory>) {
        if (itemClick.item.image.contains("prof_")) getListItemsCategory(itemClick.item.id)
        else startDetailItemFragment(itemClick)
    }

    private fun getListItemsCategory(id: Int) {
        viewModelScope.launch {
            getListItemCategoryUseCase.execute(id) {
                onComplete {
                    adapter.setItems(it)
                    router?.activity?.changedStatusRecyclerFragment(true)
                }
                onError { Log.d(TAG, "onClickItem message: ${it.message}") }
            }
        }
    }

    private fun startDetailItemFragment(itemClick: ItemClick<ItemCategory>) {
        router?.startReplaceFragment(DetailItemFragment.getInstance(itemClick.item.id), DetailItemFragment.TAG)
    }
}