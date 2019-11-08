package com.blackstone.ratusha.ui.screens.detailed


import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.viewModelScope
import com.blackstone.domain.entity.Item
import com.blackstone.domain.entity.ItemRecipeFull
import com.blackstone.domain.entity.TypeRecipe
import com.blackstone.domain.usecases.*
import com.blackstone.ratusha.app.App
import com.blackstone.ratusha.ui.base.BaseViewModel
import com.blackstone.ratusha.ui.adapter.recipe.RecipeAdapter
import com.blackstone.ratusha.ui.screens.controller.ControllerRouter
import com.blackstone.ratusha.utils.CalculationsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Evgeny Butov
 * @created 19.04.2019
 */

class DetailItemViewModel : BaseViewModel<ControllerRouter>() {

    private val counter: ObservableField<String> = ObservableField<String>()
    private val counterVisible: ObservableBoolean = ObservableBoolean()
    private val typeItemNoAlchemy: ObservableBoolean = ObservableBoolean(true)
    private val total: ObservableField<String> = ObservableField<String>()
    private val item: ObservableField<Item> = ObservableField<Item>()
    private val craft: ObservableField<String> = ObservableField<String>("1")
    private val itemNoAlchemy: ObservableBoolean = ObservableBoolean()

    private var listItem = mutableListOf<ItemRecipeFull>()
    private var plusTime: Long = 0
    private var minusTime: Long = 0
    private var textVisibility: Boolean = false

    private val adapter = RecipeAdapter()

    @Inject lateinit var getRecipeItemUseCase: GetRecipeItemUseCase
    @Inject lateinit var getRecipeAlchemyUseCase: GetRecipeAlchemyUseCase
    @Inject lateinit var getItemCategoryUseCase: GetItemCategoryUseCase

    init {
        App.appComponent.runInject(this)
    }

    fun getAdapter(): RecipeAdapter = adapter
    fun getItem(): ObservableField<Item> = item
    fun getCounter(): ObservableField<String> = counter
    fun getTotal(): ObservableField<String> = total
    fun getCraft(): ObservableField<String> = craft
    fun getItemNoAlchemy(): ObservableBoolean = itemNoAlchemy
    fun getTypeItemNoAlchemy(): ObservableBoolean = typeItemNoAlchemy
    fun getCounterVisible(): ObservableBoolean = counterVisible

    fun getItemAndRecipe(idItem: Int) {
        typeItemNoAlchemy.set(idItem > 50)

        viewModelScope.launch {
            getItemCategoryUseCase.execute(idItem) {
                onComplete { item.set(it) }
                onError { Log.d(TAG, "getItem message: ${it.message}") }
            }

            if (idItem > 50) {
                itemNoAlchemy.set(true)
                getRecipeItem(idItem)
            } else getAlchemyRecipe(idItem)
        }
    }

    private fun setTotal(listItem: List<ItemRecipeFull>) {
        total.set(CalculationsUtils.getTotalPriceRecipte(listItem))
    }

    private fun getAlchemyRecipe(id: Int) {
        getRecipeAlchemyUseCase.execute(id) {
            onComplete {
                adapter.setItems(it)
                setTotal(it)
            }
            onError {
                Log.d(TAG, "getAlchemyRecipe message: ${it.message}")
            }
        }
    }

    private fun getRecipeItem(id: Int) {
        getRecipeItemUseCase.execute(id) {
            onComplete {
                adapter.setItems(it)
                listItem = it.toMutableList()
                setTotal(it)
            }
            onError { Log.d(TAG, "getRecipeItem message: ${it.message}") }
        }
    }


    fun onPlusTouched(v: View, event: MotionEvent): Boolean {

        if (craft.get().toString().isEmpty()) {
            craft.set("1")
        }

        if (craft.get()?.toInt() ?: 1 < 999) {

            if (System.currentTimeMillis() - 100 > plusTime && event.actionMasked == MotionEvent.ACTION_MOVE) {
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

        if (craft.get().toString().isEmpty()) {
            craft.set("1")
        }

        if (craft.get()?.toInt() ?: 1 > 1) {
            if (System.currentTimeMillis() - 100 > minusTime && event.actionMasked == MotionEvent.ACTION_MOVE) {
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
            counterVisible.set(true)
            textVisibility = true
        }

        if (event == MotionEvent.ACTION_UP && textVisibility) {
            counterVisible.set(false)
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

            adapter.setItems(returnList)
            if (returnList.isNotEmpty()) setTotal(returnList)
        }
    }

}