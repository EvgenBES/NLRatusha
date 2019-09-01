package com.blackstone.ratusha.ui.screens.detailed


import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.viewModelScope
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.extension.convertToLinkedList
import com.blackstone.domain.usecases.*
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.adapter.recipe.RecipeAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.04.2019
 */

class DetailItemViewModel : BaseViewModel<ControllerRouter>() {

    val counter: ObservableField<String> = ObservableField<String>("1")
    val typeItem: ObservableBoolean = ObservableBoolean(true)
    val total: ObservableField<String> = ObservableField<String>("0.00")
    val image: ObservableField<Int> = ObservableField<Int>()
    val name: ObservableField<String> = ObservableField<String>("Неизвестный предмет")
    val price: ObservableField<String> = ObservableField<String>("Цена: 0")
    val reputation: ObservableField<String> = ObservableField<String>("х0")
    val craft: ObservableField<String> = ObservableField<String>("1")
    val itemNoAlchemy: ObservableBoolean = ObservableBoolean(false)
    var listItem = mutableListOf<ItemRecipeFull>()

    private var plusTime: Long = 0
    private var minusTime: Long = 0
    private var textVisibility: Boolean = false

    val adapter = RecipeAdapter()

    @Inject lateinit var getRecipeItemUseCase: GetRecipeItemUseCase
    @Inject lateinit var getRecipeAlchemyUseCase: GetRecipeAlchemyUseCase
    @Inject lateinit var getItemCategoryUseCase: GetItemCategoryUseCase

    init {
        App.appComponent.runInject(this)
    }

    fun getItemAndRecipe(idItem: Int) {
        typeItem.set(idItem > 50)

        getItemCategoryUseCase.execute(idItem) {
            onComplete {
                image.set(getImageResources(it.image))
                name.set(it.name)
                price.set("Цена: ${it.price} / ")
                reputation.set("x${it.reputation} (${it.countItemRep})")
            }
            onError { Log.d(TAG, "getItem message: ${it.message}") }
        }

        if (idItem > 50) getRecipeItem(idItem) else getAlchemyRecipe(idItem)
    }

    private fun getImageResources(image: String): Int {
        return router?.activity?.resources?.getIdentifier("ic_$image", "drawable", router?.activity?.packageName)
            ?: router?.activity?.resources?.getIdentifier("ic_iw_empty", "drawable", router?.activity?.packageName) ?: 0
    }

    private fun setTotal(listItem: List<ItemRecipeFull>) {
        total.set(CalculationsUtils.getTotalPriceRecipte(listItem))
    }

    private fun getAlchemyRecipe(id: Int) {
        viewModelScope.launch {
            getRecipeAlchemyUseCase.execute(id) {
                onComplete {
                    adapter.setItems(it)
                    setTotal(it)
                    if (it.isEmpty()) {} //todo show empty data
                }
                onError {
                    Log.d(TAG, "getAlchemyRecipe message: ${it.message}")
                }
            }
        }
    }

    private fun getRecipeItem(id: Int) {
        getRecipeItemUseCase.execute(id) {
            onComplete {
                adapter.setItems(it)
                listItem = it
                setTotal(it)
                if (it.isEmpty()) {} //todo show empty data
                itemNoAlchemy.set(true)
            }
            onError { Log.d(TAG, "getRecipeItem message: ${it.message}") }
        }
    }


    fun onPlusTouched(v: View, event: MotionEvent): Boolean {

        if (craft.get().toString().length == 0) {
            craft.set("1")
        }

        if (craft.get()?.toInt() ?: 1 < 999) {

            if (System.currentTimeMillis() - 50 > plusTime && event.actionMasked == MotionEvent.ACTION_MOVE) {
                plusTime = System.currentTimeMillis()
                val count: Int = craft.get()?.toInt() ?: 1
                craft.set(count.plus(1).toString())
            }
            counter.set(craft.get())
        }

        setVisibilityTextCount(event.actionMasked)

        return true // or return false, depending on what you want to do
    }

    fun onMinusTouched(v: View, event: MotionEvent): Boolean {

        if (craft.get().toString().length == 0) {
            craft.set("1")
        }

        if (craft.get()?.toInt() ?: 1 > 1) {
            if (System.currentTimeMillis() - 50 > minusTime) {
                minusTime = System.currentTimeMillis()
                val count: Int = craft.get()?.toInt() ?: 1
                craft.set(count.minus(1).toString())
            }
            counter.set(craft.get())
        }

        setVisibilityTextCount(event.actionMasked)

        return true // or return false, depending on what you want to do
    }

    private fun setVisibilityTextCount(event: Int) {
        if (textVisibility.not()) {
        //    router?.showTextCounter()
            textVisibility = true
        }

        if (event == MotionEvent.ACTION_UP && textVisibility) {
      //      router?.hideTextCounter()
            textVisibility = false
        }
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty()) {
            val result: Int = s.toString().toInt()
            val returnList = mutableListOf<ItemRecipeFull>()

            listItem.forEach {
                returnList.add(ItemRecipeFull(it.id, it.image, it.name, it.price, it.number * result, it.type))
            }

            adapter.setItems(returnList.convertToLinkedList())
            if (returnList.isNotEmpty()) setTotal(returnList)
        }
    }

}