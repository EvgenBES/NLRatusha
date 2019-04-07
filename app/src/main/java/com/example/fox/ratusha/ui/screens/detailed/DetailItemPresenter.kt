package com.example.fox.ratusha.ui.screens.detailed

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.example.fox.ratusha.data.usecases.GetItemsUseCase
import com.example.fox.ratusha.data.usecases.GetRecipeUseCase
import com.example.fox.ratusha.di.app.App
import com.example.fox.ratusha.ui.base.BasePresenter
import com.example.fox.ratusha.ui.base.recycler.RecyclerRecipeAdapter
import com.example.fox.ratusha.ui.entity.ItemRecipeFull
import com.example.fox.ratusha.utils.CalculationsUtils.getTotalPriceRecipte
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 24.02.2019
 */

class DetailItemPresenter(view: DetailItemView) : BasePresenter<DetailItemRouter, DetailItemView>(view) {

    val typeItem: ObservableBoolean = ObservableBoolean(true)
    val total: ObservableField<String> = ObservableField<String>("0.00")

    val adapter = RecyclerRecipeAdapter()
    private val emptyItemRecipe = listOf<ItemRecipeFull>(ItemRecipeFull(id = 0))

    init {
        App.appComponent.runInject(this)
    }

    @Inject
    lateinit var getRecipeUseCase: GetRecipeUseCase

    @Inject
    lateinit var getItemsUseCase: GetItemsUseCase

    fun getItemAndRecipe(idItem: Int) {
        typeItem.set(idItem > 50)

        val disposableA = getItemsUseCase.getItem(idItem).subscribeBy(
        onNext = {
            view.setItem(itemName = it.name, itemImage = it.image, itemPrice = it.price, itemReputation = it.reputation, itemCountRep = it.countItemRep)
        },
        onError = { Log.d("AAQQ", "getItem message: ${it.message}") }
        )
        addToDisposible(disposableA)


        if (idItem > 50) getRecipeItem(idItem) else getAlchemyRecipe(idItem)
    }

    private fun setTotal(listItem: List<ItemRecipeFull>) {
        total.set(getTotalPriceRecipte(listItem))
    }


    private fun getAlchemyRecipe(id: Int) {
        val disposableB = getRecipeUseCase.getRecipeAlchemy(id).subscribeBy (
                onNext = {
                    adapter.setItems(it)
                    setTotal(it)
                    if (it.isEmpty()) adapter.setItems(emptyItemRecipe)
                },
                onError = { Log.d("AAQQ", "getCategoryDao message: ${it.message}") }
        )
        addToDisposible(disposableB)
    }

    private fun getRecipeItem(id: Int) {
        val disposableB = getRecipeUseCase.getRecipeItem(id).subscribeBy (
                onNext = {
                    adapter.setItems(it)
                    setTotal(it)
                    if (it.isEmpty()) adapter.setItems(emptyItemRecipe)
                },
                onError = { Log.d("AAQQ", "getCategoryDao message: ${it.message}") }
        )
        addToDisposible(disposableB)
    }

}