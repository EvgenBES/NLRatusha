package com.example.fox.ratusha.ui.screens.detailed

import android.util.Log
import com.example.fox.ratusha.data.usecases.GetRecipeUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.RecyclerRecipeAdapter
import com.example.fox.ratusha.ui.entity.ItemRecipe
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */

class DetailItemPresenter(view: DetailItemView) : BasePresenter<DetailItemRouter, DetailItemView>(view) {

    val adapter = RecyclerRecipeAdapter()
    private val emptyItemRecipe = listOf<ItemRecipe>(ItemRecipe(0))

    init {
        App.appComponent.runInject(this)
    }

    @Inject
    lateinit var getRecipeUseCase: GetRecipeUseCase

    fun getItemRecipe(idRecipe: Int) {
        val disposable = getRecipeUseCase.getRecipeOrder(idRecipe).subscribeBy (
                onNext = {
                    adapter.setItems(it)
                    setTotal(it)
                    if (it.isEmpty()) adapter.setItems(emptyItemRecipe)
                },
                onError = { Log.d("AAQQ", "getCategoryDao message: ${it.message}") }
        )
        addToDisposible(disposable)
    }

    private fun setTotal(listItem: List<ItemRecipe>) {
        var total = 0.0
        listItem.forEach { total += it.number * it.price }
        view.setTotal(total.toString())
    }

}