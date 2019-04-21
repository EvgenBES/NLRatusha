package com.blackstone.ratusha.ui.screens.detailed


import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.usecases.GetItemsUseCase
import com.blackstone.domain.usecases.GetRecipeUseCase
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.mvvm.BaseViewModel
import com.blackstone.ratusha.ui.base.recycler.RecyclerRecipeAdapter
import com.blackstone.ratusha.utils.CalculationsUtils
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.04.2019
 */

class DetailItemModel : BaseViewModel<DetailItemRouter>() {

    companion object {
        const val TAG = "Ratusha DetailItemModel"
    }

    val typeItem: ObservableBoolean = ObservableBoolean(true)
    val total: ObservableField<String> = ObservableField<String>("0.00")
    val image: ObservableField<Int> = ObservableField<Int>()
    val name: ObservableField<String> = ObservableField<String>("Неизвестный предмет")
    val price: ObservableField<String> = ObservableField<String>("Цена: 0")
    val reputation: ObservableField<String> = ObservableField<String>("х0")
    val craft: ObservableField<String> = ObservableField<String>("1")

    private var plusTime: Long = 0
    private var minusTime: Long = 0

    val adapter = RecyclerRecipeAdapter()
    private val emptyItemRecipe = listOf<ItemRecipeFull>(ItemRecipeFull(id = 0))

    @Inject
    lateinit var getRecipeUseCase: GetRecipeUseCase

    @Inject
    lateinit var getItemsUseCase: GetItemsUseCase

    init {
        App.appComponent.runInject(this)
    }

    fun getItemAndRecipe(idItem: Int) {
        typeItem.set(idItem > 50)

        val disposableA = getItemsUseCase.getItem(idItem).subscribeBy(
            onNext = {
                image.set(getImageResources(it.image))
                name.set(it.name)
                price.set("Цена: ${it.price} / ")
                reputation.set("x${it.reputation} (${it.countItemRep})")
            },
            onError = { Log.d(TAG, "getItem message: ${it.message}") }
        )
        addToDisposable(disposableA)

        if (idItem > 50) getRecipeItem(idItem) else getAlchemyRecipe(idItem)
    }

    private fun getImageResources(image: String): Int {
        return router?.activity?.resources?.getIdentifier("ic_$image", "drawable", router?.activity?.packageName) ?:
        router?.activity?.resources?.getIdentifier("ic_iw_empty", "drawable", router?.activity?.packageName) ?: 0
    }

    private fun setTotal(listItem: List<ItemRecipeFull>) {
        total.set(CalculationsUtils.getTotalPriceRecipte(listItem))
    }

    private fun getAlchemyRecipe(id: Int) {
        val disposableB = getRecipeUseCase.getRecipeAlchemy(id).subscribeBy (
            onNext = {
                adapter.setItems(it)
                setTotal(it)
                if (it.isEmpty()) adapter.setItems(emptyItemRecipe)
            },
            onError = { Log.d(TAG, "getAlchemyRecipe message: ${it.message}") }
        )
        addToDisposable(disposableB)
    }

    private fun getRecipeItem(id: Int) {
        val disposableB = getRecipeUseCase.getRecipeItem(id).subscribeBy (
            onNext = {
                adapter.setItems(it)
                setTotal(it)
                if (it.isEmpty()) adapter.setItems(emptyItemRecipe)
            },
            onError = { Log.d(TAG, "getRecipeItem message: ${it.message}") }
        )
        addToDisposable(disposableB)
    }


    fun onPlusTouched(v: View, event: MotionEvent): Boolean {
        if (System.currentTimeMillis() - 250 > plusTime && craft.get()?.toInt() ?: 1 < 999) {
            plusTime = System.currentTimeMillis()
            val count: Int = craft.get()?.toInt() ?: 1
            craft.set(count.plus(1).toString())
        }
        return true // or return false, depending on what you want to do
    }

    fun onMinusTouched(v: View, event: MotionEvent): Boolean {
        if (System.currentTimeMillis() - 250 > minusTime && craft.get()?.toInt() ?: 1 > 1) {
            minusTime = System.currentTimeMillis()
            val count: Int = craft.get()?.toInt() ?: 1
            craft.set(count.minus(1).toString())
        }
        return true // or return false, depending on what you want to do

    }
}